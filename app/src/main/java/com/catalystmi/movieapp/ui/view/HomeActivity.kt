package com.catalystmi.movieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.catalystmi.movieapp.R
import com.catalystmi.movieapp.data.model.Results
import com.catalystmi.movieapp.data.repository.MovieRepository
import com.catalystmi.movieapp.databinding.ActivityHomeBinding
import com.catalystmi.movieapp.ui.adapter.TrendingMovieAdapter
import com.catalystmi.movieapp.ui.fragment.NowPlayingFragment
import com.catalystmi.movieapp.ui.fragment.PopularFragment
import com.catalystmi.movieapp.ui.fragment.TopRatedFragment
import com.catalystmi.movieapp.ui.fragment.UpcomingFragment
import com.catalystmi.movieapp.ui.viewmodel.MovieViewModel
import com.catalystmi.movieapp.util.Constants
import com.catalystmi.movieapp.util.MovieViewModelFactory
import com.catalystmi.movieapp.util.RetrofitInstance

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var trendingMovieAdapter: TrendingMovieAdapter
    private val trendingMovieList = mutableListOf<Results>()
    private var selectedTab = 1
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieRepository(RetrofitInstance.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        fetchAllData()
        observeData()
        adListener()
    }

    private fun adListener() {
        binding.llNowPlaying.setOnClickListener {
            manageTab(1)
        }
        binding.llUpcoming.setOnClickListener {
            manageTab(2)
        }
        binding.llTopRated.setOnClickListener {
            manageTab(3)
        }
        binding.llPopular.setOnClickListener {
            manageTab(4)
        }
    }

    private fun observeData() {
        movieViewModel.errorLiveData.observe(this) { errorMessage ->
            Toast.makeText(this@HomeActivity, errorMessage, Toast.LENGTH_SHORT).show()
        }

        // Fetching trending movies and updating the UI
        movieViewModel.trendingMovies.observe(this) { movies ->
            stopShimmerTrending()
            trendingMovieList.clear()
            trendingMovieList.addAll(movies)
            trendingMovieAdapter.notifyDataSetChanged()
        }
    }

    private fun init() {
        // Set up the initial fragment
        replaceFragment(NowPlayingFragment())

        startShimmerTrending()

        // Set up the RecyclerView for trending movies
        binding.rvTrending.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        trendingMovieAdapter = TrendingMovieAdapter(trendingMovieList) { movie ->
            startActivity(
                Intent(this, MovieDetailsActivity::class.java).putExtra(
                    "movie_id",
                    movie.id
                )
            )
        }
        binding.rvTrending.adapter = trendingMovieAdapter
    }

    private fun fetchAllData() {
        movieViewModel.fetchTrendingMovies(Constants.TIME_WINDOW, Constants.API_KEY)
    }

    private fun manageTab(tab: Int) {
        selectedTab = tab
        resetTab()

        when (tab) {
            1 -> {
                replaceFragment(NowPlayingFragment())
                binding.viewNowPlaying.visibility = View.VISIBLE
            }

            2 -> {
                replaceFragment(UpcomingFragment())
                binding.viewUpcoming.visibility = View.VISIBLE
            }

            3 -> {
                replaceFragment(TopRatedFragment())
                binding.viewTopRated.visibility = View.VISIBLE
            }

            4 -> {
                replaceFragment(PopularFragment())
                binding.viewPopular.visibility = View.VISIBLE
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun resetTab() {
        binding.viewNowPlaying.visibility = View.GONE
        binding.viewUpcoming.visibility = View.GONE
        binding.viewTopRated.visibility = View.GONE
        binding.viewPopular.visibility = View.GONE
    }

    private fun startShimmerTrending() {
        binding.shimmerLayoutTrending.visibility = View.VISIBLE
        binding.mainContainer.visibility = View.GONE
        binding.shimmerLayoutTrending.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
    }

    private fun stopShimmerTrending() {
        binding.shimmerLayoutTrending.stopShimmer()
        binding.shimmerLayoutTrending.visibility = View.GONE
        binding.mainContainer.visibility = View.VISIBLE
        binding.shimmerLayout.visibility = View.GONE
    }
}