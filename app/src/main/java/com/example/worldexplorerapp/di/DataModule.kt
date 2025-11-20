package com.example.worldexplorerapp.di

import android.content.Context
import androidx.room.Room
import com.example.worldexplorer.data.repository.CountryRepositoryImpl
import com.example.worldexplorer.data.repository.ICountryRepository
import com.example.worldexplorer.network.ApiService
import com.example.worldexplorerapp.data.local.dao.CountryDao
import com.example.worldexplorerapp.data.local.db.CountriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideCountryRepository(
        apiService: ApiService,
        countryDao: CountryDao
    ): ICountryRepository = CountryRepositoryImpl(
        apiService,
        countryDao = countryDao
    )
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CountriesDatabase {
        return Room.databaseBuilder(context, CountriesDatabase::class.java, "countries_database").build()
    }

    @Provides
    @Singleton
    fun provideCountryDao(database: CountriesDatabase): CountryDao {
        return database.countryDao()
    }
}