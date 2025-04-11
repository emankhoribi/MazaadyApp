package com.example.domain.usecase

import com.example.domain.repo.MoviesRepo
import com.example.domain.usecase.base.MoviesUseCase

class PopularUseCase(private val moviesRepo: MoviesRepo): MoviesUseCase {
    override suspend operator fun invoke(page: Int) = moviesRepo.getPopular(page)
}