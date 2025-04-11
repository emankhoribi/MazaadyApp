package com.example.domain.repo

import com.example.domain.entity.details.MovieDetailsResponse

interface MovieDetailsRepo {

    suspend fun getMovieDetails(id: Int): MovieDetailsResponse
}