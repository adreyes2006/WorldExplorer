package com.example.worldexplorerapp.domain

import com.example.worldexplorer.data.repository.ICountryRepository
import com.example.worldexplorerapp.data.model.Country
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: ICountryRepository
) {
    suspend operator fun invoke(): Result<List<Country>> {
        return countryRepository.getCountries()
    }
}