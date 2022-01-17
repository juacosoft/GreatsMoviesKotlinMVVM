package com.jmdev.greatsmovieskotlin.util

import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import java.lang.Exception

sealed class MoviesUiState {
    data class Success(val movies:List<MovieModel>):MoviesUiState()
    data class Loading(val isLoading:Boolean):MoviesUiState()
    data class Error(val exception: Throwable):MoviesUiState()
}