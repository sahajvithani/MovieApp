package com.catalystmi.movieapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catalystmi.movieapp.data.model.Movie
import com.catalystmi.movieapp.data.model.MovieCastResponse
import com.catalystmi.movieapp.data.model.MovieDetailsResponse
import com.catalystmi.movieapp.data.model.PlayVideoResponse
import com.catalystmi.movieapp.data.model.Results
import com.catalystmi.movieapp.data.model.TrendingMovies
import com.catalystmi.movieapp.data.repository.MovieRepository
import com.catalystmi.movieapp.util.Constants
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _trendingMovies = MutableLiveData<List<Results>>()
    val trendingMovies: LiveData<List<Results>> get() = _trendingMovies

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _movieDetails = MutableLiveData<MovieDetailsResponse>()
    val movieDetails: LiveData<MovieDetailsResponse> get() = _movieDetails

    private val _movieCast = MutableLiveData<MovieCastResponse>()
    val movieCasts: LiveData<MovieCastResponse> get() = _movieCast

    private val _movieVideo = MutableLiveData<PlayVideoResponse>()
    val movieVideo: LiveData<PlayVideoResponse> get() = _movieVideo

    private val _movieResponse = MutableLiveData<TrendingMovies>()
    val movieResponse: LiveData<TrendingMovies> get() = _movieResponse

    private val _upcomingMovieResponse = MutableLiveData<TrendingMovies>()
    val upcomingMovieResponse: LiveData<TrendingMovies> get() = _upcomingMovieResponse

    private val _topRatedMovieResponse = MutableLiveData<TrendingMovies>()
    val topRatedMovieResponse: LiveData<TrendingMovies> get() = _topRatedMovieResponse

    private val _popularMovieResponse = MutableLiveData<TrendingMovies>()
    val popularMovieResponse: LiveData<TrendingMovies> get() = _popularMovieResponse

    fun fetchTrendingMovies(timeWindow:String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getTrendingMovies(timeWindow, apiKey)
                if (response.isSuccessful) {
                    _trendingMovies.postValue(response.body()?.results) // Access body as User
                } else {
                    _errorLiveData.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorLiveData.postValue("Exception: ${e.message}")
            }
        }
    }

    fun fetchMovieDetails(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieDetails(movieId, apiKey)
                if (response.isSuccessful) {
                    _movieDetails.postValue(response.body()) // Access body as User
                } else {
                    _errorLiveData.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorLiveData.postValue("Exception: ${e.message}")
            }
        }
    }

    fun fetchMovieCast(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieCast(movieId, apiKey)
                if (response.isSuccessful) {
                    _movieCast.postValue(response.body()) // Access body as User
                } else {
                    _errorLiveData.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorLiveData.postValue("Exception: ${e.message}")
            }
        }
    }

    fun fetchVideoMovies(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getVideoMovies(movieId, apiKey)
                if (response.isSuccessful) {
                    _movieVideo.postValue(response.body()) // Access body as User
                } else {
                    _errorLiveData.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorLiveData.postValue("Exception: ${e.message}")
            }
        }
    }

    fun fetchMovies(type:String,page: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovies(type, page, apiKey)
                if (response.isSuccessful) {
                    when (type) {
                        Constants.NOW_PLAYING -> {
                            _movieResponse.postValue(response.body())
                        }
                        Constants.UPCOMING -> {
                            _upcomingMovieResponse.postValue(response.body())
                        }
                        Constants.TOP_RATED -> {
                            _topRatedMovieResponse.postValue(response.body())
                        }
                        Constants.POPULAR -> {
                            _popularMovieResponse.postValue(response.body())
                        }
                    }
                } else {
                    _errorLiveData.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorLiveData.postValue("Exception: ${e.message}")
            }
        }
    }
}