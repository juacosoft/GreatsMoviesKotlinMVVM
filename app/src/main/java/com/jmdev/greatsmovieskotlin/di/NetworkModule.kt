package com.jmdev.greatsmovieskotlin.di

import com.jmdev.greatsmovieskotlin.constants.ServerUrls.BASE_URL
import com.jmdev.greatsmovieskotlin.data.network.GreatsMoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providerRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit):GreatsMoviesApi{
        return retrofit.create(GreatsMoviesApi::class.java)
    }

}