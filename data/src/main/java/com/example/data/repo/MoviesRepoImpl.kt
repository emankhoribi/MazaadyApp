package com.example.data.repo

import com.example.data.remote.ApiServices
import com.example.data.remote.MoviesFavoritesDatabase
import com.example.domain.entity.data.Favorite
import com.example.domain.entity.movies.MovieListResponse
import com.example.domain.repo.MoviesRepo
import kotlinx.coroutines.flow.Flow

class MoviesRepoImpl(private val apiServices: ApiServices, private val db: MoviesFavoritesDatabase) : MoviesRepo {
    override suspend fun getMovieList(page: Int): MovieListResponse = apiServices.getMovieList(page)

    override fun getFavorites(): Flow<MutableList<Favorite>> = db.FavoriteDao().getFavorites()

    override suspend fun deleteRecord(favorite: Favorite): Int = db.FavoriteDao().deleteRecord(favorite)

    override suspend fun isRowIsExist(id: Int): Boolean = db.FavoriteDao().isRowIsExist(id)

    override suspend fun upsert(favorite: Favorite): Long = db.FavoriteDao().upsert(favorite)

}