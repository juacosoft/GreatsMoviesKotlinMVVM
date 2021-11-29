package com.jmdev.greatsmovieskotlin.listeners

import com.jmdev.greatsmovieskotlin.data.models.TrailerModel

interface TrailerSelected {
    fun onTRailerSelected(trailerModel: TrailerModel)
}