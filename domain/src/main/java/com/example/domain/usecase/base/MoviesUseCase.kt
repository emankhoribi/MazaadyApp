package com.example.domain.usecase.base

import com.example.domain.entity.movies.MoviesResponse

interface MoviesUseCase {
    suspend operator fun invoke(page: Int): MoviesResponse
}