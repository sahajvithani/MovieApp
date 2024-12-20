package com.catalystmi.movieapp.util

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val API_KEY = "154ad8f9017ced85e1b45f006f50d4a0"
    const val TIME_WINDOW = "day"
    const val NOW_PLAYING = "now_playing"
    const val UPCOMING = "upcoming"
    const val TOP_RATED = "top_rated"
    const val POPULAR = "popular"
    const val TIMEOUT_DURATION = 30L
    const val ERROR_MESSAGE = "Something went wrong. Please try again."
}

// https://api.themoviedb.org/3/trending/movie/day?api_key=154ad8f9017ced85e1b45f006f50d4a0
// https://api.themoviedb.org/trending/movie/day?api_key=154ad8f9017ced85e1b45f006f50d4a0