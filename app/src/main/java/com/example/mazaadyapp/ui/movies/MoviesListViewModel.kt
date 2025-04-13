package com.example.mazaadyapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.movies.Result
import com.example.domain.usecase.MoviesUseCase
import com.example.mazaadyapp.ui.utils.MoviesPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(val moviesUseCase: MoviesUseCase): ViewModel() {

    fun getMovieList(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {MoviesPaging(moviesUseCase)}).flow.cachedIn(viewModelScope)
    }
}