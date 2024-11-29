package com.kuzmin.fooddeliverytestapp.data.repository

import android.util.Log
import com.kuzmin.fooddeliverytestapp.data.db.FoodDao
import com.kuzmin.fooddeliverytestapp.data.mapper.AddressMapper
import com.kuzmin.fooddeliverytestapp.data.mapper.FoodDataDbToFoodDataMapper
import com.kuzmin.fooddeliverytestapp.data.network.ApiService
import com.kuzmin.fooddeliverytestapp.domain.Repository
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData
import com.kuzmin.fooddeliverytestapp.domain.model.food.FoodData
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val foodMapper: FoodDataDbToFoodDataMapper,
    private val addressMapper: AddressMapper,
    private val foodDao: FoodDao,
    private val apiService: ApiService
) : Repository {

    /*override suspend fun getAllFoodDataFromDb():FoodData {
        return foodMapper.mapFoodDataDbToFoodData(foodDao.getAll())
    }*/

    override suspend fun getAddressSuggestions(queryData: QueryData): List<Address> {
        val addressRequestDto = addressMapper.mapQueryDataToAddressRequestDto(queryData)

        val addressResponseDtoList = apiService.getSuggestions(addressRequestDto).suggestions ?: emptyList()

        addressResponseDtoList.forEach { addressResponseDto ->
            Log.d("QUERY", "Address: ${addressResponseDto.address}")
        }
        return addressMapper.mapAddressResponseDtoToAddressModel(addressResponseDtoList)
    }
}