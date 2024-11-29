package com.kuzmin.fooddeliverytestapp.data.network.model

import com.google.gson.annotations.SerializedName

data class AddressRequestDto(

    @SerializedName("query")
    val addressQuery: String,

    @SerializedName("locations_geo")
    val locations: List<LocationDto>? = null
)