package com.example.worldexplorerapp.ui.countries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.worldexplorerapp.R
import com.example.worldexplorerapp.model.Country

@Composable
fun CountryScreen(viewModel: CountryViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
    }

    CountryScreenContent(uiState, viewModel)
}

@Composable
private fun CountryScreenContent(
    uiState: CountryUiState,
    viewModel: CountryViewModel
) {
    when (uiState) {
        is CountryUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CountryUiState.Success -> {
            val countries = uiState.countries
            CountryScreen(countries)
        }

        is CountryUiState.Empty -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.no_countries_available))
            }
        }

        is CountryUiState.Error -> {
            val message = uiState.message
            ErrorScreen(message) {
                viewModel.refresh()
            }
        }
    }
}

@Composable
fun CountryScreen(countries: List<Country>) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(horizontal = 10.dp, vertical = 60.dp)) {
        Text(
            text = stringResource(R.string.world_explorer),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(countries) { country ->
                CountryItem(
                    name = country.name,
                    region = country.region,
                    code = country.code,
                    capital = country.capital
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CountryScreenPreview() {
    val sampleCountries = listOf(
        Country("Afghanistan", "AS", "AF", "Kabul"),
        Country("Albania", "EU", "AL", "Tirana"),
        Country("Algeria", "AF", "DZ", "Algiers")
    )
    CountryScreen(countries = sampleCountries)
}