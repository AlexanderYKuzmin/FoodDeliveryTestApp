package com.kuzmin.fooddeliverytestapp.data.db

import android.content.res.Resources
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kuzmin.fooddeliverytestapp.util.FoodDefaultData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class RoomDbInitializer(
    private val foodDaoProvider: Provider<FoodDao>,
    private val res: Resources
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Log.d("DB", "Creating roomInitializer db")
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase(res)
        }
    }

    private suspend fun populateDatabase(res: Resources) {

    }
}
