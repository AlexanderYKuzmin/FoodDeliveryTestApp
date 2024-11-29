package com.kuzmin.fooddeliverytestapp.domain.usecases

import com.kuzmin.fooddeliverytestapp.domain.PrefManager
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import javax.inject.Inject

class StoreLocationDataToDatastoreUseCaseImpl @Inject constructor(
    private val prefManager: PrefManager
) : StoreLocationDataToDatastoreUseCase {

    override suspend fun storeLocationData(locationData: Location) =
        prefManager.storeLocationData(locationData)
}

interface StoreLocationDataToDatastoreUseCase {
    suspend fun storeLocationData(locationData: Location)
}