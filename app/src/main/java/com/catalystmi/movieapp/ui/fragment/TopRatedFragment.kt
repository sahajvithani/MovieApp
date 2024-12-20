package com.catalystmi.movieapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catalystmi.movieapp.data.repository.MovieRepository
import com.catalystmi.movieapp.databinding.FragmentMoviesBinding
import com.catalystmi.movieapp.ui.adapter.MovieAdapter
import com.catalystmi.movieapp.ui.view.MovieDetailsActivity
import com.catalystmi.movieapp.ui.viewmodel.MovieViewModel
import com.catalystmi.movieapp.util.Constants
import com.catalystmi.movieapp.util.MovieViewModelFactory
import com.catalystmi.movieapp.util.RetrofitInstance

class TopRatedFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var movieAdapter: MovieAdapter
//    private lateinit var movieViewModel: MovieViewModel
    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieRepository(RetrofitInstance.apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        // Initialize the RecyclerView adapter
        movieAdapter = MovieAdapter() { movie ->
            activity?.startActivity(
                Intent(requireContext(), MovieDetailsActivity::class.java).putExtra(
                    "movie_id",
                    movie.id
                )
            )
        }
        binding.rvAll.layoutManager = GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL, false)
        binding.rvAll.adapter = movieAdapter

        // Set up ViewModel
//        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        // Observe data from ViewModel
        observeMovies()
        startShimmerAnimation()
        movieViewModel.fetchMovies(Constants.TOP_RATED, currentPage, Constants.API_KEY)

        // Implement pagination
        setupPagination()

        return binding.root
    }

    private fun observeMovies() {
        movieViewModel.topRatedMovieResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                stopShimmerAnimation()
                movieAdapter.submitList(response.results)
                isLoading = false
                if (response.results.isEmpty()) {
                    isLastPage = true
                }
            }
        }

        movieViewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            stopShimmerAnimation()
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun startShimmerAnimation() {
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
    }

    private fun stopShimmerAnimation() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
    }

    private fun setupPagination() {
        binding.rvAll.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = binding.rvAll.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if (totalItemCount <= lastVisibleItemPosition + 5) {
                        currentPage++
                        isLoading = true
                        movieViewModel.fetchMovies(Constants.TOP_RATED, currentPage, Constants.API_KEY)
                    }
                }
            }
        })
    }
}