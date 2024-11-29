package com.kuzmin.fooddeliverytestapp.domain.usecases

import com.kuzmin.fooddeliverytestapp.domain.PrefManager
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import javax.inject.Inject

class WriteAddressToDatastoreUseCaseImpl @Inject constructor(
    private val prefManager: PrefManager
) : WriteAddressToDatastoreUseCase {

    override suspend fun writeAddress(address: Address) {
        prefManager.writeAddress(
            String.format("%s, %s", address.street, address.house)
        )
    }
}

interface WriteAddressToDatastoreUseCase {
    suspend fun writeAddress(address: Address)
}