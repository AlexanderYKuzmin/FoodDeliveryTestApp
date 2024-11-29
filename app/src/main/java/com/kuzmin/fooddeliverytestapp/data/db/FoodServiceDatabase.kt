package com.kuzmin.fooddeliverytestapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kuzmin.fooddeliverytestapp.data.db.model.CatalogDb
import com.kuzmin.fooddeliverytestapp.data.db.model.DiscountDb
import com.kuzmin.fooddeliverytestapp.data.db.model.PromoActionDb
import com.kuzmin.fooddeliverytestapp.data.db.model.PromoSectionDb

@Database(
    entities = [
        PromoSectionDb::class,
        PromoActionDb::class,
        CatalogDb::class,
        DiscountDb::class
    ],
    version = 1
)
abstract class FoodServiceDatabase : RoomDatabase(){
    abstract fun foodDao(): FoodDao
}
