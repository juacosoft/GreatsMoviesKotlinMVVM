package com.jmdev.greatsmovieskotlin.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.jmdev.greatsmovieskotlin.data.MoviesRepository
import com.jmdev.greatsmovieskotlin.data.models.MovieDetailModel
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.data.models.TrailerModel
import com.jmdev.greatsmovieskotlin.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {

    suspend fun invokePopularMovies():List<MovieModel>?=repository.getPopularMovies()
    suspend fun invokeTopRatedMovies():List<MovieModel>?=repository.getTopratedMovies()
    suspend fun invokeDetailMovie():MovieDetailModel?=repository.getDetailMovie()
    suspend fun invokeTrailersMovie():List<TrailerModel>?=repository.getTrailersMovie()
    //suspend fun invokeSearchMovie():List<MovieModel>?=repository.searchMovie()
}