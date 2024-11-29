package com.kuzmin.fooddeliverytestapp.data.network.model

import com.google.gson.annotations.SerializedName

data class AddressResponseDto(

    @SerializedName("value")
    val address: String,

    @SerializedName("unrestricted_value")
    val fullAddress: String,

    @SerializedName("data")
    val data: AddressDataDto? = null
)