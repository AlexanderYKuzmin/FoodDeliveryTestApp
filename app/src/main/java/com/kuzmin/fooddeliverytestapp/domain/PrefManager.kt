package com.kuzmin.fooddeliverytestapp.domain

import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import kotlinx.coroutines.flow.Flow

interface PrefManager {
    suspend fun readLocationData(): Location
    suspend fun storeLocationData(locationData: Location)
    suspend fun writeAddress(address: String)
    suspend fun readAddress(): Flow<String>
}