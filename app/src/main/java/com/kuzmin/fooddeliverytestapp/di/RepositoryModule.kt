package com.kuzmin.fooddeliverytestapp.di

import com.kuzmin.fooddeliverytestapp.data.datastore.PrefManagerImpl
import com.kuzmin.fooddeliverytestapp.data.repository.RepositoryImpl
import com.kuzmin.fooddeliverytestapp.domain.PrefManager
import com.kuzmin.fooddeliverytestapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    fun bindPrefManager(prefManagerImpl: PrefManagerImpl): PrefManager
}