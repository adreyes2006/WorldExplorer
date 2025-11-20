package com.example.worldexplorer.data.repository

import com.example.worldexplorerapp.model.Country


interface ICountryRepository {
    suspend fun getCountries(): Result<List<Country>>
}