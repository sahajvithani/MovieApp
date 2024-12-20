package com.catalystmi.movieapp.data.api

import com.catalystmi.movieapp.data.model.MovieCastResponse
import com.catalystmi.movieapp.data.model.MovieDetailsResponse
import com.catalystmi.movieapp.data.model.MovieResponse
import com.catalystmi.movieapp.data.model.PlayVideoResponse
import com.catalystmi.movieapp.data.model.TrendingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse>

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") movieId: String,
        @Query("api_key") apiKey: String
    ): Response<TrendingMovies>

    @GET("movie/{type}")
    suspend fun getMovies(
        @Path("type") type: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<TrendingMovies>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieCastResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<PlayVideoResponse>

}