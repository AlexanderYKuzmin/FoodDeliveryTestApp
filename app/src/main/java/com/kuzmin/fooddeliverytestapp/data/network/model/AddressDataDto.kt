package com.kuzmin.fooddeliverytestapp.data.network.model

import com.google.gson.annotations.SerializedName

data class AddressDataDto(
    @SerializedName("country")
    val country: String,

    @SerializedName("region")
    val region: String,

    @SerializedName("region_with_type")
    val regionWithType: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("city_with_type")
    val cityWithType: String,

    @SerializedName("street")
    val street: String,

    @SerializedName("street_with_type")
    val streetWithType: String,

    @SerializedName("street_type")
    val streetType: String,

    @SerializedName("house")
    val house: String
)