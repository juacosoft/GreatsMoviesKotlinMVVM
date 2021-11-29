package com.jmdev.greatsmovieskotlin.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.jmdev.greatsmovieskotlin.data.MoviesRepository
import com.jmdev.greatsmovieskotlin.data.PageController
import com.jmdev.greatsmovieskotlin.data.models.MovieDetailModel
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.data.models.TrailerModel

import com.jmdev.greatsmovieskotlin.domain.MoviesUseCase
import com.jmdev.greatsmovieskotlin.util.NetworkConnection
import com.jmdev.greatsmovieskotlin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val pageController: PageController,
    private val repository: MoviesRepository,
    val isConection: NetworkConnection
) :ViewModel() {
    val trailers=MutableLiveData<List<TrailerModel>>()
    //val isConection=networkConnection
    fun movie(idMovie:Int): LiveData<Resource<MovieDetailModel>> {
        pageController.movieSelected=idMovie
       return repository.getDetailMovieLocalRemote(idMovie).asLiveData()
    }



     fun fechtTrailers(){
        viewModelScope.launch {
            val result=repository.getTrailersMovie()
            Log.d("DetailMovieViewModel","$result")
            if(!result.isNullOrEmpty()){
                trailers.postValue(result)
            }
        }


    }



}