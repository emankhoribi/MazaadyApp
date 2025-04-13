package com.example.domain.usecase

import com.example.domain.entity.data.Favorite
import com.example.domain.repo.MoviesRepo
import kotlinx.coroutines.flow.Flow

class MoviesLocalUseCase(private val moviesRepo: MoviesRepo) {
    fun getFavorites() : Flow<MutableList<Favorite>> = moviesRepo.getFavorites()

    suspend fun deleteRecord(favorite: Favorite) = moviesRepo.deleteRecord(favorite)

     fun isRowIsExist(id : Int) : Flow<Boolean> = moviesRepo.isRowIsExist(id)

    suspend fun upsert(favorite: Favorite): Long = moviesRepo.upsert(favorite)
}