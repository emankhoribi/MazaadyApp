package com.example.data.remote.repo

import com.example.data.remote.ApiServices
import com.example.domain.entity.details.MovieDetailsResponse
import com.example.domain.repo.MovieDetailsRepo

class MovieDetailsRepoImpl(private val apiServices: ApiServices) : MovieDetailsRepo {
    override suspend fun getMovieDetails(id: Int): MovieDetailsResponse =
        apiServices.getMovieDetails(id)
}