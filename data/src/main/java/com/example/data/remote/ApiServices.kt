package com.example.data.remote

import com.example.domain.entity.details.MovieDetailsResponse
import com.example.domain.entity.movies.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("discover/movie?language=en-US")
    suspend fun getMovieList(@Query("page") page: Int): MovieListResponse

    @GET("movie/{id}?language=en-US")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetailsResponse


}