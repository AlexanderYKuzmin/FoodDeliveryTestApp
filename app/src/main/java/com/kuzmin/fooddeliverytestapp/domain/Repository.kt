package com.kuzmin.fooddeliverytestapp.domain

import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData
import com.kuzmin.fooddeliverytestapp.domain.model.food.FoodData

interface Repository {

    //suspend fun getAllFoodDataFromDb(): FoodData

    suspend fun getAddressSuggestions(queryData: QueryData): List<Address>
}