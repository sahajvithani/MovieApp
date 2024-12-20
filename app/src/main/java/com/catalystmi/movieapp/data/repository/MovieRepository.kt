package com.catalystmi.movieapp.data.repository

import com.catalystmi.movieapp.data.api.ApiService
import com.catalystmi.movieapp.data.model.MovieCastResponse
import com.catalystmi.movieapp.data.model.MovieDetailsResponse
import com.catalystmi.movieapp.data.model.MovieResponse
import com.catalystmi.movieapp.data.model.PlayVideoResponse
import com.catalystmi.movieapp.data.model.TrendingMovies
import retrofit2.Response

class MovieRepository(private val apiService: ApiService) {

    suspend fun getPopularMovies(apiKey: String): Response<MovieResponse> {
        return apiService.getPopularMovies(apiKey)
    }

    suspend fun getMovieDetails(movieId: Int, apiKey: String): Response<MovieDetailsResponse> {
        return apiService.getMovieDetails(movieId, apiKey)
    }

    suspend fun getTrendingMovies(timeWindow: String, apiKey: String): Response<TrendingMovies> {
        return apiService.getTrendingMovies(timeWindow, apiKey)
    }

    suspend fun getMovieCast(movieId: Int, apiKey: String): Response<MovieCastResponse> {
        return apiService.getMovieCast(movieId, apiKey)
    }

    suspend fun getVideoMovies(movieId: Int, apiKey: String): Response<PlayVideoResponse> {
        return apiService.getVideoMovies(movieId, apiKey)
    }

    suspend fun getMovies(type:String, page: Int, apiKey: String): Response<TrendingMovies> {
        return apiService.getMovies(type, page, apiKey)
    }
}