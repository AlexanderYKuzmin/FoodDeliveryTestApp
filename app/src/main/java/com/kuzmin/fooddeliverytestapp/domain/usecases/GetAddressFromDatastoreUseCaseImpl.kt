package com.kuzmin.fooddeliverytestapp.domain.usecases

import com.kuzmin.fooddeliverytestapp.domain.PrefManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAddressFromDatastoreUseCaseImpl @Inject constructor(
    private val prefManager: PrefManager
) : GetAddressFromDatastoreUseCase {

    override suspend fun getAddress(): Flow<String> = prefManager.readAddress()
}

interface GetAddressFromDatastoreUseCase {

    suspend fun getAddress(): Flow<String>
}