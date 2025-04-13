package com.example.mazaadyapp.di.module

import com.example.data.remote.ApiServices
import com.example.data.repo.MovieDetailsRepoImpl
import com.example.data.repo.MoviesRepoImpl
import com.example.domain.repo.MovieDetailsRepo
import com.example.domain.repo.MoviesRepo
import com.example.data.remote.MoviesFavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(apiServices: ApiServices, db: MoviesFavoritesDatabase): MoviesRepo = MoviesRepoImpl(apiServices, db)

    @Provides
    fun providesMovieDetailsRepository(apiServices: ApiServices): MovieDetailsRepo = MovieDetailsRepoImpl(apiServices)
}