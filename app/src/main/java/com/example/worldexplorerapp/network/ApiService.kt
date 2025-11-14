package com.example.worldexplorer.network

import com.example.worldexplorer.data.dto.CountryDto
import retrofit2.http.GET

interface ApiService {
    @GET("countries.json")
    suspend fun getCountries(): List<CountryDto>
}