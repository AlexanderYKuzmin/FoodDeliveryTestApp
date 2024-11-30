package com.kuzmin.fooddeliverytestapp.data.mapper

import com.kuzmin.fooddeliverytestapp.data.network.model.AddressRequestDto
import com.kuzmin.fooddeliverytestapp.data.network.model.AddressResponseDto
import com.kuzmin.fooddeliverytestapp.data.network.model.LocationDto
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData
import javax.inject.Inject

class AddressMapper @Inject constructor() {

    fun mapQueryDataToAddressRequestDto(queryData: QueryData): AddressRequestDto {
        return AddressRequestDto(
            addressQuery = queryData.query,
            locations = queryData.location?.let {
                listOf(mapLocationToLocationDto(it))
            }
        )
    }

    fun mapAddressResponseDtoToAddressModel(addressResponseDtoList: List<AddressResponseDto>): List<Address> {
        if (addressResponseDtoList.isEmpty()) return emptyList()
        return addressResponseDtoList.map { mapAddressResponseDtoToAddress(it) }
    }

    private fun mapAddressResponseDtoToAddress(addressResponseDto: AddressResponseDto): Address {
        with(addressResponseDto) {
            return Address(
                address = address,
                fullAddress = fullAddress,
                country = data?.country ?: "",
                region = data?.region ?: "",
                regionWithType = data?.regionWithType ?: "",
                cityWithType = data?.cityWithType ?: "",
                city = data?.city ?: "",
                streetWithType = data?.streetWithType ?: "",
                streetType = data?.streetType ?: "",
                street = data?.street ?: "",
                house = data?.house ?: ""
            )
        }
    }

    fun mapLocationToLocationDto(location: Location): LocationDto {
        return LocationDto(
            latitude = location.latitude,
            longitude = location.longitude,
            radius = location.radius
        )
    }

    private fun mapLocationDtoToLocation(locationDto: LocationDto): Location {
        return Location(
            latitude = locationDto.latitude,
            longitude = locationDto.longitude,
            radius = locationDto.radius
        )
    }
}