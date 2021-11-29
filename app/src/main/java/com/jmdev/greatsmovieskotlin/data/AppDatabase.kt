package com.jmdev.greatsmovieskotlin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jmdev.greatsmovieskotlin.data.converters.ObjectConverters
import com.jmdev.greatsmovieskotlin.data.models.MovieDetailModel
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.data.models.TrailerModel


@Database(entities = [MovieModel::class, MovieDetailModel::class,TrailerModel::class],version = 18,exportSchema = false)
@TypeConverters(ObjectConverters::class)
abstract class AppDatabase :RoomDatabase() {
    abstract fun moviesDao():MoviesDao
}