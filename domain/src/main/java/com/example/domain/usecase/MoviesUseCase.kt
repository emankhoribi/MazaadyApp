package com.example.domain.usecase

import com.example.domain.repo.MoviesRepo

class MoviesUseCase(private val moviesRepo: MoviesRepo) {
    suspend  operator fun invoke(page: Int) = moviesRepo.getMovieList(page)
}