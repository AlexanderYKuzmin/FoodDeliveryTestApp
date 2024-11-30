package com.kuzmin.fooddeliverytestapp.data.repository

import com.kuzmin.fooddeliverytestapp.data.mapper.AddressMapper
import com.kuzmin.fooddeliverytestapp.data.network.ApiService
import com.kuzmin.fooddeliverytestapp.domain.Repository
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val addressMapper: AddressMapper,
    private val apiService: ApiService
) : Repository {

    override suspend fun getAddressSuggestions(queryData: QueryData): List<Address> {
        val addressRequestDto = addressMapper.mapQueryDataToAddressRequestDto(queryData)

        val addressResponseDtoList = apiService.getSuggestions(addressRequestDto).suggestions ?: emptyList()

        return addressMapper.mapAddressResponseDtoToAddressModel(addressResponseDtoList)
    }

    override suspend fun getAddressByLocation(location: Location): List<Address> {
        val locationDto = addressMapper.mapLocationToLocationDto(location)

        val addressResponseDtoList = apiService.getGeolocation(locationDto).suggestions ?: emptyList()

        return addressMapper.mapAddressResponseDtoToAddressModel(addressResponseDtoList)
    }
}