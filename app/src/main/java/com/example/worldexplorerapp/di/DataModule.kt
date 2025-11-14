package com.example.worldexplorerapp.di

import com.example.worldexplorer.data.repository.CountryRepositoryImpl
import com.example.worldexplorer.data.repository.ICountryRepository
import com.example.worldexplorer.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideCountryRepository(
        apiService: ApiService
    ): ICountryRepository = CountryRepositoryImpl(apiService)
}