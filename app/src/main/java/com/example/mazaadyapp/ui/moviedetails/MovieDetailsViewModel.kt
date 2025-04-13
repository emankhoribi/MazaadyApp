package com.example.mazaadyapp.ui.moviedetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.details.MovieDetailsResponse
import com.example.domain.usecase.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(val movieDetailsUseCase: MovieDetailsUseCase): ViewModel() {


    private val _movieFLow: MutableStateFlow<MovieDetailsResponse?> = MutableStateFlow(null)
    val movieFLow = _movieFLow

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            try {

                _movieFLow.value =
                    movieDetailsUseCase(id)


            } catch (e: Exception) {
                Log.e("Movies", e.message.toString())
            }
        }
    }

}