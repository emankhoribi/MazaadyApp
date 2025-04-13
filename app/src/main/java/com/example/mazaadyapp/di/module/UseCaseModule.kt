package com.example.mazaadyapp.di.module

import com.example.domain.repo.MovieDetailsRepo
import com.example.domain.repo.MoviesRepo
import com.example.domain.usecase.MovieDetailsUseCase
import com.example.domain.usecase.MoviesLocalUseCase
import com.example.domain.usecase.MoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideMoviesUseCase(moviesRepo: MoviesRepo) : MoviesUseCase =
        MoviesUseCase(moviesRepo)

    @Provides
    fun provideMovieDetailsUseCase(movieDetailsRepo: MovieDetailsRepo) : MovieDetailsUseCase =
        MovieDetailsUseCase(movieDetailsRepo)

    @Provides
    fun provideMoviesLocalUseCase(moviesRepo: MoviesRepo) : MoviesLocalUseCase =
        MoviesLocalUseCase(moviesRepo)
}