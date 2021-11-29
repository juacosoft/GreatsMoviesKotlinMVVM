package com.jmdev.greatsmovieskotlin.data.models

import com.google.gson.annotations.SerializedName


data class VideosResultModel(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<TrailerModel>
)