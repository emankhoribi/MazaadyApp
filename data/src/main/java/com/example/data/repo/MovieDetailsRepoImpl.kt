package com.example.data.repo

import com.example.data.remote.ApiServices
import com.example.data.remote.MoviesFavoritesDatabase
import com.example.domain.entity.data.Favorite
import com.example.domain.entity.details.MovieDetailsResponse
import com.example.domain.repo.MovieDetailsRepo
import kotlinx.coroutines.flow.Flow

class MovieDetailsRepoImpl(private val apiServices: ApiServices) : MovieDetailsRepo {
    override suspend fun getMovieDetails(id: Int): MovieDetailsResponse =
        apiServices.getMovieDetails(id)



}