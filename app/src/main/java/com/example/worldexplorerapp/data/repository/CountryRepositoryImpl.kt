package com.example.worldexplorer.data.repository

import android.util.Log
import com.example.worldexplorer.data.dto.toDomain
import com.example.worldexplorer.network.ApiService
import com.example.worldexplorerapp.data.local.dao.CountryDao
import com.example.worldexplorerapp.data.local.toDomain
import com.example.worldexplorerapp.data.local.toEntitu
import com.example.worldexplorerapp.model.Country

class CountryRepositoryImpl(
    private val apiService: ApiService,
    private val countryDao: CountryDao
) : ICountryRepository {

    override suspend fun getCountries(): Result<List<Country>> {
        return try {
            val remote = apiService.getCountries().map { it.toDomain() }
            Log.d("remote api call", remote.first().capital)
            countryDao.upsertCountries(remote.map { it.toEntitu() })
            val local: List<Country> = countryDao.getCountries().map { it.toDomain() }
            Log.d("local db call", local.first().capital)
            Result.success(local)

        } catch (e: Exception) {
            val local: List<Country> = countryDao.getCountries().map { it.toDomain() }
            if (local.isNotEmpty()) Result.success(local) else Result.failure(e)
        }
    }
}

