package com.jmdev.greatsmovieskotlin.data.network

import android.util.Log
import com.jmdev.greatsmovieskotlin.data.PageController
import com.jmdev.greatsmovieskotlin.data.models.MovieDetailModel
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.data.models.TrailerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api:GreatsMoviesApi,
    private val pageController: PageController
) {

    suspend fun getPopularMovies():List<MovieModel>{
        return withContext(Dispatchers.IO){
            val response=api.getPopularMovies(pageController.page)
            Log.d("Response","$response")
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getTopratedMovies():List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getTopRatedMovies(pageController.page)
            Log.d("Response", "$response")
            response?.body()?.results ?: emptyList()
        }
    }
    suspend fun getDetailMovie():MovieDetailModel{
        return withContext(Dispatchers.IO){
            Log.d("Response","callApi ${pageController.movieSelected}")
            val response=api.getDetailMovie(pageController.movieSelected)

            response.body()!!
        }
    }

    suspend fun getTrailers():List<TrailerModel>{
        return withContext(Dispatchers.IO){
            val response=api.getTrailers(pageController.movieSelected.toString())
            Log.d("Response","$response")
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getSearchMovies():List<MovieModel>{
        return withContext(Dispatchers.IO){
            val response=api.searchMovie(pageController.query,pageController.page)
            Log.d("Response","$response")
            response.body()?.results ?: emptyList()
        }
    }


}