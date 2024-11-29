package com.kuzmin.fooddeliverytestapp.di

import com.kuzmin.fooddeliverytestapp.domain.usecases.GetAddressFromDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetAddressFromDatastoreUseCaseImpl
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetAddressSuggestionsUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetAddressSuggestionsUseCaseImpl
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetLocationDataFromDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetLocationDataFromDatastoreUseCaseImpl
import com.kuzmin.fooddeliverytestapp.domain.usecases.StoreLocationDataToDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.StoreLocationDataToDatastoreUseCaseImpl
import com.kuzmin.fooddeliverytestapp.domain.usecases.WriteAddressToDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.WriteAddressToDatastoreUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    /*@Binds
    fun bindGetAllFoodDataFromDbUseCase(
        getAllFoodDataFromDbUseCaseImpl: GetAllFoodDataFromDbUseCaseImpl
    ): GetAllFoodDataFromDbUseCase*/

    @Binds
    fun bindGetAddressSuggestionsUseCase(
        addressSuggestionsUseCaseImpl: GetAddressSuggestionsUseCaseImpl
    ): GetAddressSuggestionsUseCase

    @Binds
    fun bindGetLocationDataFromDatastoreUseCase(
        getLocationDataFromDatastoreUseCaseImpl: GetLocationDataFromDatastoreUseCaseImpl
    ): GetLocationDataFromDatastoreUseCase

    @Binds
    fun bindStoreLocationDataToDatastoreUseCase(
        storeLocationDataToDatastoreUseCaseImpl: StoreLocationDataToDatastoreUseCaseImpl
    ): StoreLocationDataToDatastoreUseCase

    @Binds
    fun bindWriteAddressToDatastoreUseCase(
        writeAddressToDatastoreUseCaseImpl: WriteAddressToDatastoreUseCaseImpl
    ): WriteAddressToDatastoreUseCase

    @Binds
    fun bindGetAddressFromDatastoreUseCase(
        getAddressFromDatastoreUseCaseImpl: GetAddressFromDatastoreUseCaseImpl
    ): GetAddressFromDatastoreUseCase
}