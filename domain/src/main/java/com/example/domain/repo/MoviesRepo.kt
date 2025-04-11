package com.example.domain.repo

import com.example.domain.entity.movies.MoviesResponse

interface MoviesRepo {

    suspend fun getNowPlaying(page: Int): MoviesResponse
    suspend fun getPopular(page: Int): MoviesResponse
    suspend fun getUpcoming(page: Int): MoviesResponse
}