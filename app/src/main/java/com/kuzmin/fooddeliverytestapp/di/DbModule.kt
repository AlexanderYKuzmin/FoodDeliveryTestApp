package com.kuzmin.fooddeliverytestapp.di

import android.content.Context
import androidx.room.Room
import com.kuzmin.fooddeliverytestapp.data.db.FoodDao
import com.kuzmin.fooddeliverytestapp.data.db.FoodServiceDatabase
import com.kuzmin.fooddeliverytestapp.data.db.RoomDbInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Singleton
    @Provides
    fun provideFoodServiceDatabase(
        @ApplicationContext appContext: Context,
        foodDaoProvider: Provider<FoodDao>
    ): FoodServiceDatabase {
        return Room.databaseBuilder(
            appContext,
            FoodServiceDatabase::class.java, "food-db",
        )
            .addCallback(
                RoomDbInitializer(
                    foodDaoProvider = foodDaoProvider,
                    res = appContext.resources
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(foodServiceDatabase: FoodServiceDatabase): FoodDao {
        return foodServiceDatabase.foodDao()
    }
}