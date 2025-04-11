package com.example.data.remote

import com.example.domain.entity.details.MovieDetailsResponse
import com.example.domain.entity.movies.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("now_playing?language=en-US")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): MoviesResponse

    @GET("popular?language=en-US")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesResponse

    @GET("upcoming?language=en-US")
    suspend fun getUpcomingMovies(@Query("page") page: Int): MoviesResponse

    @GET("{id}?language=en-US")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetailsResponse
}