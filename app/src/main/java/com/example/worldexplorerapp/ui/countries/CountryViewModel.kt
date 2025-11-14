package com.example.worldexplorerapp.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldexplorerapp.domain.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<CountryUiState>(CountryUiState.Loading)
    val uiState: StateFlow<CountryUiState> = _uiState

    fun loadCountries() {
        viewModelScope.launch {
            val result = getCountriesUseCase()
            _uiState.value = result.fold(
                onSuccess = { countries ->
                    if (countries.isEmpty()) CountryUiState.Empty
                    else CountryUiState.Success(countries)
                },
                onFailure = { throwable ->
                    CountryUiState.Error(throwable.message ?: "Unknown error")
                }
            )
        }
    }

    fun refresh() = viewModelScope.launch {
        _uiState.update { CountryUiState.Loading }
        loadCountries()
    }
}