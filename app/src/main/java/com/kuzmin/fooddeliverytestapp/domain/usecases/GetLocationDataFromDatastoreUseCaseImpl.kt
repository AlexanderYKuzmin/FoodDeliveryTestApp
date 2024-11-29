package com.kuzmin.fooddeliverytestapp.domain.usecases

import com.kuzmin.fooddeliverytestapp.domain.PrefManager
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import javax.inject.Inject

class GetLocationDataFromDatastoreUseCaseImpl @Inject constructor(
  private val prefManager: PrefManager
) : GetLocationDataFromDatastoreUseCase {

    override suspend fun getLocationDataFromDatastore() = prefManager.readLocationData()
}

interface GetLocationDataFromDatastoreUseCase {
    suspend fun getLocationDataFromDatastore(): Location
}