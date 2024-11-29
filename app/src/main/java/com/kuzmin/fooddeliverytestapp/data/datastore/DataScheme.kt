package com.kuzmin.fooddeliverytestapp.data.datastore

import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataScheme {
    val LATITUDE = doublePreferencesKey("latitude")
    val LONGITUDE = doublePreferencesKey("longitude")

    val ADDRESS = stringPreferencesKey("address")
}