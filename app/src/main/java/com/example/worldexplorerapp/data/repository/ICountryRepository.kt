package com.example.worldexplorer.data.repository

import com.example.worldexplorerapp.data.model.Country


interface ICountryRepository {
    suspend fun getCountries(): Result<List<Country>>
}