package com.jmdev.greatsmovieskotlin.listeners

import com.jmdev.greatsmovieskotlin.data.models.MovieModel

interface MovieSelected {
    fun onMovieSelected(movieModel: MovieModel)
}