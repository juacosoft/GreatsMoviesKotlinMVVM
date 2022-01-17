package com.jmdev.greatsmovieskotlin.data

import androidx.room.*
import com.jmdev.greatsmovieskotlin.data.converters.ObjectConverters
import com.jmdev.greatsmovieskotlin.data.models.MovieDetailModel
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.data.models.TrailerModel
import kotlinx.coroutines.flow.Flow



@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    suspend fun getLocalMovies():List<MovieModel>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getLocalPopularMovies(): Flow<List<MovieModel>>

    @Query("SELECT * FROM movies ORDER BY vote_average DESC")
    fun getLocalTopRatedMovies():Flow<List<MovieModel>>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%' COLLATE NOCASE")
    fun getLocalsearchMovies(query:String):Flow<List<MovieModel>>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%' COLLATE NOCASE")
    suspend fun getsearchLocalMovies(query:String):List<MovieModel>

    @Query("SELECT * FROM movies WHERE id=:id")
    suspend fun getMovie(id: Int):MovieModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies:List<MovieModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserMovie(movieModel: MovieModel)

    @Query("DELETE FROM movies")
    suspend fun deleteMovies()


    @TypeConverters(ObjectConverters::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(movieDetailModel: MovieDetailModel)


    @TypeConverters(ObjectConverters::class)
    @Query("SELECT * FROM moviesdetails WHERE id=:id")
    fun getDetailLocalMovie(id:Int):Flow<MovieDetailModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrailers(trailersModel: List<TrailerModel>)

    @Query("SELECT * FROM trailers ")
    fun getTrailers():Flow<List<TrailerModel>>



}