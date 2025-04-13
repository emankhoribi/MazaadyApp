package com.example.domain.repo

import com.example.domain.entity.data.Favorite
import com.example.domain.entity.movies.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepo {

    suspend fun getMovieList(page: Int): MovieListResponse

    fun getFavorites() : Flow<MutableList<Favorite>>

    suspend fun deleteRecord(favorite: Favorite) : Int

    suspend fun isRowIsExist(id : Int) : Boolean

    suspend fun upsert(favorite: Favorite): Long

}