package com.example.mazaadyapp.ui.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.data.Favorite
import com.example.domain.entity.movies.Result
import com.example.domain.usecase.MoviesLocalUseCase
import com.example.domain.usecase.MoviesUseCase
import com.example.mazaadyapp.ui.utils.MoviesPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    val moviesUseCase: MoviesUseCase,
    val moviesLocalUseCase: MoviesLocalUseCase
) : ViewModel() {

    private val _moviesLocalFlow: MutableStateFlow<MutableList<Favorite>?> = MutableStateFlow(null)
    val moviesLocalFlow = _moviesLocalFlow

    fun getMovieList(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { MoviesPaging(moviesUseCase) }).flow.cachedIn(viewModelScope)
    }

    fun getFavorites() {
        viewModelScope.launch {
            try {
                moviesLocalUseCase.getFavorites().collect {
                    _moviesLocalFlow.value = it
                }
            } catch (e: Exception) {
                Log.e("FavoriteList", e.message.toString())
            }
        }
    }

    fun upsertFavorite(movie: Result) {
        val favorite = Favorite(movie.id, movie.title)
        viewModelScope.launch {
            try {
                moviesLocalUseCase.upsert(favorite)
            } catch (e: Exception) {
                Log.e("Upsert", e.message.toString())
            }
        }


    }

     fun deleteRecord(movie: Result) {
        val favorite = Favorite(movie.id, movie.title)
        viewModelScope.launch {
            try {
                moviesLocalUseCase.deleteRecord(favorite)
            } catch (e: Exception) {
                Log.e("Delete", e.message.toString())
            }
        }
    }


}