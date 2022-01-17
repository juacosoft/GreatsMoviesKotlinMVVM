package com.jmdev.greatsmovieskotlin.data

import android.util.Log
import androidx.lifecycle.asLiveData
import com.jmdev.greatsmovieskotlin.data.models.MovieDetailModel
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.data.models.TrailerModel
import com.jmdev.greatsmovieskotlin.data.network.MovieService
import com.jmdev.greatsmovieskotlin.util.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api:MovieService,
    private val localMovies:MoviesDao

) {

    fun getPopularLocalRemote()= networkBoundResource(
        query = {
            localMovies.getLocalPopularMovies()
        },
        fetch = {
            delay(2000)
            api.getPopularMovies()
        },
        saveFetchResult = {movies->
            //localMovies.deleteMovies()
            localMovies.insertMovies(movies)
            Log.d("MoviesRepository","$movies")
        }
    )
    fun getTopRatedLocalRemote()= networkBoundResource(
        query = {
            localMovies.getLocalTopRatedMovies()
        },
        fetch = {
            delay(2000)
            api.getTopratedMovies()
        },
        saveFetchResult = {movies->
            localMovies.insertMovies(movies)
            Log.d("MoviesRepository","$movies")
        }
    )
    fun getDetailMovieLocalRemote(id:Int)= networkBoundResource(
        query = {
            localMovies.getDetailLocalMovie(id)
        },
        fetch = {
            delay(2000)
            api.getDetailMovie()
        },
        saveFetchResult = {movie->
            Log.d("MoviesRepository","Getdetail: $movie")
            localMovies.insertDetailMovie(movie)

        }
    )
    /*fun getSearchLocalRemoteMovie(query:String,page:Int)= networkBoundResource(
        query = {
            localMovies.getLocalsearchMovies(query)
        },
        fetch = {

                api.getSearchMovies(query,page)
                delay(2000)


        },
        saveFetchResult = {movie->
            Log.d("MoviesRepository","Getdetail: $movie")
            localMovies.insertMovies(movie)

        }
    )*/
    fun getSearchLocalRemoteMovie(query:String,page:Int,defaultDispatcher: CoroutineDispatcher) =
        flow {
            while (true){
                val movies=api.getSearchMovies(query,page)
                emit(movies)
                delay(50000)
            }
        }.flowOn(defaultDispatcher)
        .onEach {
            Log.d("onEach","$it")
            localMovies.insertMovies(it)
        }.catch {exeption->
            exeption.printStackTrace()
            emit(localMovies.getsearchLocalMovies(query))
        }


    suspend fun getMovieById(id:Int):MovieModel{
        return localMovies.getMovie(id)
    }
    suspend fun getLocalPopular():List<MovieModel>{
        val response=localMovies.getLocalMovies()
        Log.d("MoviesRepository","$response")
        return response
    }
    suspend fun getPopularMovies():List<MovieModel>{
        val response=api.getPopularMovies()
        //localMovies.insertMovies(response)
        Log.d("Movielist","$response")
        return response
    }
    suspend fun getTopratedMovies():List<MovieModel>{
        val response=api.getTopratedMovies()
        //localMovies.insertMovies(response)
        Log.d("Movielist","$response")
        return response
    }
    suspend fun getDetailMovie():MovieDetailModel{
        val response=api.getDetailMovie()
        Log.d("MovieDetail","$response")
        return response
    }
    suspend fun getTrailersMovie():List<TrailerModel>{
        val response=api.getTrailers()
        Log.d("Trailers","$response")
        return response
    }
    /*suspend fun searchMovie():List<MovieModel>{
        val response=api.getSearchMovies()
        Log.d("SearchMovie","$response")
        return response
    }*/
}