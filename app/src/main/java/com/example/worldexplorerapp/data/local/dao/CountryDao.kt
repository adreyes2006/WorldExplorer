package com.example.worldexplorerapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.worldexplorerapp.data.local.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries")
    suspend fun getCountries(): List<CountryEntity>

    @Upsert
    suspend fun upsertCountries(countries: List<CountryEntity>)

}