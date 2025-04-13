package com.example.mazaadyapp.ui.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.movies.Result
import com.example.domain.usecase.MoviesUseCase

class MoviesPaging(private val moviesUseCase: MoviesUseCase) :
PagingSource<Int, Result>(){
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val data = moviesUseCase(page)

            LoadResult.Page(
                data = data.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}