package com.example.domain.usecase

import com.example.domain.entity.details.MovieDetailsResponse
import com.example.domain.repo.MovieDetailsRepo

class MovieDetailsUseCase(private val movieDetailsRepo: MovieDetailsRepo) {

    suspend operator fun invoke(id: Int): MovieDetailsResponse =
        movieDetailsRepo.getMovieDetails(id)
}