package com.catalystmi.movieapp.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.catalystmi.movieapp.R
import com.catalystmi.movieapp.data.model.Cast
import com.catalystmi.movieapp.data.repository.MovieRepository
import com.catalystmi.movieapp.databinding.ActivityMovieDetailsBinding
import com.catalystmi.movieapp.ui.adapter.CastAdapter
import com.catalystmi.movieapp.ui.viewmodel.MovieViewModel
import com.catalystmi.movieapp.util.Constants
import com.catalystmi.movieapp.util.MovieViewModelFactory
import com.catalystmi.movieapp.util.RetrofitInstance
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailsActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieRepository(RetrofitInstance.apiService))
    }

    private lateinit var binding: ActivityMovieDetailsBinding
    private var isAbout = false
    private lateinit var castAdapter: CastAdapter
    private val movieCastList = mutableListOf<Cast>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        getMoviesDetails()
        observeData()
        adListener()
    }

    @SuppressLint("SetTextI18n")
    private fun observeData() {
        movieViewModel.movieDetails.observe(this) { movieDetails ->
            binding.tvMovieTitle.text = movieDetails.title

            binding.tvDetails.text =
                "${movieDetails.releaseDate?.let { formatDate(it) }} - ${movieDetails.genres.joinToString { it.name.toString() }} - ${movieDetails.runtime} Minutes"
            binding.tvDate.text = getYearFromDate(movieDetails.releaseDate)
            binding.tvDetails.isSelected = true
            binding.tvDuration.text = movieDetails.runtime.toString() + " Minutes"
            binding.tvMovieDetails.text = movieDetails.overview

            Glide.with(this).load(Constants.IMAGE_URL + movieDetails.posterPath)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.rivPoster)

            Glide.with(this).load(Constants.IMAGE_URL + movieDetails.backdropPath)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.rivBanner)
        }

        movieViewModel.movieCasts.observe(this) { movieCasts ->
            stopShimmer()
            movieCastList.clear()
            movieCastList.addAll(movieCasts.cast)
            castAdapter.notifyDataSetChanged()
        }

        movieViewModel.errorLiveData.observe(this) { errorMessage ->
            Toast.makeText(this@MovieDetailsActivity, errorMessage, Toast.LENGTH_SHORT).show()
        }

        movieViewModel.movieVideo.observe(this) { movieVideo ->
            startActivity(
                Intent(
                    this@MovieDetailsActivity,
                    VideoPlayerActivity::class.java
                ).putExtra("key", movieVideo.results[0].key)
            )
        }
    }

    private fun init() {
        binding.rvCast.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        castAdapter = CastAdapter(movieCastList)
        binding.rvCast.adapter = castAdapter
    }

    @SuppressLint("NewApi")
    private fun getYearFromDate(releaseDate: String?): String {
        val localDate = LocalDate.parse(releaseDate)
        val year = localDate.year
        return year.toString()
    }

    @SuppressLint("NewApi")
    fun formatDate(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")

        val parsedDate = LocalDate.parse(inputDate, inputFormatter)
        return parsedDate.format(outputFormatter)
    }

    private fun getMoviesDetails() {
        movieViewModel.fetchMovieDetails(intent.getIntExtra("movie_id", 0), Constants.API_KEY)
    }

    private fun adListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.llAboutMovie.setOnClickListener {
            manageTab(true)
        }
        binding.llCast.setOnClickListener {
            startShimmer()
            manageTab(false)
            getCastData()
        }

        binding.rivBanner.setOnClickListener {
            movieViewModel.fetchVideoMovies(intent.getIntExtra("movie_id", 0), Constants.API_KEY)
        }
    }

    private fun startShimmer() {
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.rvCast.visibility = View.GONE
    }

    private fun stopShimmer() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
        binding.rvCast.visibility = View.VISIBLE
    }

    private fun getCastData() {
        movieViewModel.fetchMovieCast(intent.getIntExtra("movie_id", 0), Constants.API_KEY)
    }

    private fun manageTab(b: Boolean) {
        isAbout = b

        if (isAbout) {
            binding.viewAboutMovie.visibility = View.VISIBLE
            binding.viewCast.visibility = View.GONE
            binding.tvMovieDetails.visibility = View.VISIBLE
            binding.rvCast.visibility = View.GONE
        } else {
            binding.viewAboutMovie.visibility = View.GONE
            binding.viewCast.visibility = View.VISIBLE
            binding.tvMovieDetails.visibility = View.GONE
            binding.rvCast.visibility = View.VISIBLE
        }
    }
}