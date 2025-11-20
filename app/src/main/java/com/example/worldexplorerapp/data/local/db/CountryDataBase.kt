package com.example.worldexplorerapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.worldexplorerapp.data.local.dao.CountryDao
import com.example.worldexplorerapp.data.local.entity.CountryEntity

@Database(
    entities = [CountryEntity::class],
    version = 1,
    exportSchema = true
)
abstract class CountriesDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}