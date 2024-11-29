package com.kuzmin.fooddeliverytestapp.data.network.model

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("lat")
    val latitude: Double,

    @SerializedName("lon")
    val longitude: Double,

    @SerializedName("radius_meters")
    val radius: Int
)
