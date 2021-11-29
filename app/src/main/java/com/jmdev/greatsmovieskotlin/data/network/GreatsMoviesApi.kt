package com.jmdev.greatsmovieskotlin.data.network

import com.jmdev.greatsmovieskotlin.constants.ServerUrls.API_KEY
import com.jmdev.greatsmovieskotlin.data.models.MovieDetailModel
import com.jmdev.greatsmovieskotlin.data.models.MoviesResponseModel
import com.jmdev.greatsmovieskotlin.data.models.VideosResultModel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.Query

interface GreatsMoviesApi {

    @GET("movie/popular?api_key=$API_KEY&language=en-US")
    suspend fun getPopularMovies(
        @Query("page") page:Int
    ): Response<MoviesResponseModel>

    @GET("movie/top_rated?api_key=$API_KEY&language=en-US")
    suspend fun getTopRatedMovies(
        @Query("page") page:Int
    ): Response<MoviesResponseModel>

    @GET("movie/{movie_id}?api_key=$API_KEY&language=en-US")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId:Int
    ):Response<MovieDetailModel>

    @GET("movie/{movie_id}/videos?api_key=$API_KEY&language=en-US")
    suspend fun getTrailers(
        @Path("movie_id") movieId:String
    ):Response<VideosResultModel>

    @GET("search/movie?api_key=$API_KEY&include_adult=false&language=en-US")
    suspend fun searchMovie(
        @Query("query")query:String,@Query("page")page:Int
    ):Response<MoviesResponseModel>
}