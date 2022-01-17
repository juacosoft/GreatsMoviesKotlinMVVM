package com.jmdev.greatsmovieskotlin.ui.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.jmdev.greatsmovieskotlin.data.MoviesRepository
import com.jmdev.greatsmovieskotlin.data.PageController
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.domain.MoviesUseCase
import com.jmdev.greatsmovieskotlin.util.MoviesUiState
import com.jmdev.greatsmovieskotlin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeMoreViewModel @Inject constructor(
    //private val moviesUseCase: MoviesUseCase,
    private val pageController: PageController,
    private val repository: MoviesRepository
) :ViewModel() {

    private val moviesList=MutableLiveData<List<MovieModel>>()
    
    val isLoading=MutableLiveData<Boolean>()
    val isError=MutableLiveData<Boolean>()



    init {
        isError.postValue(false)
        pageController.page=1
    }
    lateinit var popularList : LiveData<Resource<List<MovieModel>>>


    fun fecthAllMovies(option:String,page:Int,queryText:String?) {
        pageController.page=page
        when(option){
            "popular"->{
                popularList= repository.getPopularLocalRemote().asLiveData()
            }
            "toprated"->{
                popularList= repository.getTopRatedLocalRemote().asLiveData()
            }
            "query"->{
                if(!queryText.isNullOrEmpty()){
                    viewModelScope.launch {
                        //_uiState.value=MoviesUiState.Loading(true)
                        isLoading.postValue(true)
                        repository.getSearchLocalRemoteMovie(queryText,page,Dispatchers.IO)
                            .collect {
                                it.also { moviesList.postValue(it) }
                                isLoading.postValue(false)
                            }

                    }
                    //popularList= repository.getSearchLocalRemoteMovie(queryText,page).asLiveData()

                }
            }
            else ->{
                Log.e("Error","no Opcion")
            }

        }


    }

    /*fun fetchPopularMovies(page: Int){
        pageController.page=page
        viewModelScope.launch {
            isLoading.postValue(true)
            val result=moviesUseCase.invokePopularMovies()
            setMovieList(result!!)
            isLoading.postValue(false)
        }
    }
    fun fetchTopRatedMovies(page:Int){
        pageController.page=page
        viewModelScope.launch {
            isLoading.postValue(true)
            val result=moviesUseCase.invokeTopRatedMovies()
            Log.d("SeeMore","$result")
            setMovieList(result!!)
            isLoading.postValue(false)
        }

    }
    fun fetchSearchMovies(page:Int,query:String){
        pageController.page=page
        pageController.query=query
        viewModelScope.launch {
            isLoading.postValue(true)
            val result=moviesUseCase.invokeSearchMovie()
            Log.d("SeeMore","$result")
            setMovieList(result!!)
            isLoading.postValue(false)
        }
    }
    private fun setMovieList(result:List<MovieModel>){
        if(!result.isNullOrEmpty()){
            if(moviesList.value.isNullOrEmpty()){
                moviesList.postValue(result)
            }else{
                val _temp=moviesList.value?.toMutableList()
                _temp?.addAll(result)
                moviesList.postValue(_temp)
            }
        }else{
            isError.postValue(true)
        }

    }*/
    fun getListMovies()=moviesList

}