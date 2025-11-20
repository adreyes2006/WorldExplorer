package com.example.worldexplorerapp.ui.countries

import com.example.worldexplorerapp.model.Country


sealed interface CountryUiState {
    object Loading : CountryUiState
    data class Success(val countries: List<Country>) : CountryUiState
    data class Error(val message: String) : CountryUiState
    object Empty : CountryUiState
}