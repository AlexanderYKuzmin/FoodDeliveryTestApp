package com.kuzmin.fooddeliverytestapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.kuzmin.fooddeliverytestapp.data.datastore.DataScheme.LATITUDE
import com.kuzmin.fooddeliverytestapp.data.datastore.DataScheme.LONGITUDE
import com.kuzmin.fooddeliverytestapp.domain.PrefManager
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import com.kuzmin.fooddeliverytestapp.util.AppConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PrefManagerImpl @Inject constructor(
    @ApplicationContext val context: Context
) : PrefManager {

    private val datastore = context.dataStore

    override suspend fun storeLocationData(locationData: Location) {
        datastore.edit { prefs ->
            prefs[LATITUDE] = locationData.latitude
            prefs[LONGITUDE] = locationData.longitude
        }
    }

    override suspend fun readLocationData(): Location {
        return datastore.data
            .map {
                Location(
                    latitude = it[LATITUDE] ?: AppConstants.LATITUDE_NONE,
                    longitude = it[LONGITUDE] ?: AppConstants.LONGITUDE_NONE
                )
            }.first()
    }

    override suspend fun writeAddress(address: String) {
        datastore.edit { prefs ->
            prefs[DataScheme.ADDRESS] = address
        }
    }

    override suspend fun readAddress(): Flow<String> {
        return datastore.data
            .map {
                it[DataScheme.ADDRESS] ?: ""
            }
    }
}