package com.example.worldexplorer.data.repository

import com.example.worldexplorer.data.dto.toDomain
import com.example.worldexplorer.network.ApiService
import com.example.worldexplorerapp.data.model.Country

class CountryRepositoryImpl(
    private val apiService: ApiService
) : ICountryRepository {

    override suspend fun getCountries(): Result<List<Country>> {
        return try {
            val countries = apiService.getCountries().map { it.toDomain() }
            Result.success(countries)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

