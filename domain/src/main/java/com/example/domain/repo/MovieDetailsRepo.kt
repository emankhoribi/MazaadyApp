package com.example.domain.repo

import com.example.domain.entity.data.Favorite
import com.example.domain.entity.details.MovieDetailsResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepo {

    suspend fun getMovieDetails(id: Int): MovieDetailsResponse
}