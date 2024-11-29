package com.kuzmin.fooddeliverytestapp.data.network

import com.kuzmin.fooddeliverytestapp.data.network.model.AddressRequestDto
import com.kuzmin.fooddeliverytestapp.data.network.model.AddressResponseDto
import com.kuzmin.fooddeliverytestapp.data.network.model.ResponseJsonContainer
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(".")
    suspend fun getSuggestions(
        @Body addressRequestDto: AddressRequestDto
    ): ResponseJsonContainer
}