package com.example.data.remote.repo

import com.example.data.remote.ApiServices
import com.example.domain.entity.movies.MoviesResponse
import com.example.domain.repo.MoviesRepo

class MoviesRepoImpl(private val apiServices: ApiServices) : MoviesRepo {
    override suspend fun getNowPlaying(page: Int): MoviesResponse = apiServices.getNowPlayingMovies(page)

    override suspend fun getPopular(page: Int): MoviesResponse = apiServices.getPopularMovies(page)

    override suspend fun getUpcoming(page: Int): MoviesResponse = apiServices.getUpcomingMovies(page)
}