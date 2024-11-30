package com.kuzmin.fooddeliverytestapp.domain

import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData

interface Repository {

    suspend fun getAddressByLocation(location: Location): List<Address>

    suspend fun getAddressSuggestions(queryData: QueryData): List<Address>
}