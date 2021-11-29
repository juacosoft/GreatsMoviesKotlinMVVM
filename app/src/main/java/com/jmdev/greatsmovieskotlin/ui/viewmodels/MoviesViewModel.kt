package com.jmdev.greatsmovieskotlin.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.jmdev.greatsmovieskotlin.data.MoviesRepository
import com.jmdev.greatsmovieskotlin.data.PageController
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.domain.MoviesUseCase
import com.jmdev.greatsmovieskotlin.util.NetworkConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val pageController: PageController,
    private val repository: MoviesRepository

) :ViewModel() {

    init {
        pageController.page=1
    }
    val popularList=repository.getPopularLocalRemote().asLiveData()
    val topratedList=repository.getTopRatedLocalRemote().asLiveData()






}