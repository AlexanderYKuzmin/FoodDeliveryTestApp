package com.kuzmin.fooddeliverytestapp.domain.model.address

import com.kuzmin.fooddeliverytestapp.util.AppConstants
import com.kuzmin.fooddeliverytestapp.util.AppConstants.LATITUDE_NONE
import com.kuzmin.fooddeliverytestapp.util.AppConstants.LONGITUDE_NONE
import com.kuzmin.fooddeliverytestapp.util.AppConstants.RADIUS_DEFAULT

data class Location(
    val latitude: Double = LATITUDE_NONE,

    val longitude: Double = LONGITUDE_NONE,

    val radius: Int = RADIUS_DEFAULT
) {

    fun isNone() = latitude == LATITUDE_NONE || longitude == LONGITUDE_NONE
}
