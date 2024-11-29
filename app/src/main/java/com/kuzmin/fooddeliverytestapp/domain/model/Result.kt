package com.kuzmin.fooddeliverytestapp.domain.model

import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData
import com.kuzmin.fooddeliverytestapp.domain.model.food.FoodData

sealed class Result {
    class UpdateAddressSuccess(val address: String) : Result()

    class Error(val message: String) : Result()
}