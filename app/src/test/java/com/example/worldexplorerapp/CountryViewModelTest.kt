package com.example.worldexplorerapp

import com.example.worldexplorerapp.domain.GetCountriesUseCase
import com.example.worldexplorerapp.ui.countries.CountryUiState
import com.example.worldexplorerapp.data.model.Country
import com.example.worldexplorerapp.ui.countries.CountryViewModel
import org.junit.Assert.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {
    private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: CountryViewModel
    private val getCountriesUseCase : GetCountriesUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = CountryViewModel(getCountriesUseCase)
    }

    @Test
    fun `loadCountries returns Success when repository returns data`() = runTest {

        val sampleCountries = listOf(
            Country("Afghanistan", "AS", "AF", "Kabul"),
            Country("Brazil", "SA", "BR", "Brasilia")
        )
        coEvery { getCountriesUseCase() } returns Result.success(sampleCountries)

        viewModel.loadCountries()

        val state = viewModel.uiState.first()
        assert(state is CountryUiState.Success)
        state as CountryUiState.Success
        assertEquals(2, state.countries.size)
    }

    @Test
    fun `loadCountries returns Error when repository throws exception`() = runTest {
        val errorMessage = "Network failed"
        coEvery { getCountriesUseCase() } returns Result.failure(kotlin.Exception(errorMessage))

        viewModel.loadCountries()

        val state = viewModel.uiState.first()
        assert(state is CountryUiState.Error)
        state as CountryUiState.Error
        assertEquals(errorMessage, state.message)
    }

    @Test
    fun `loadCountries returns Empty when repository returns empty list`() = runTest(
        UnconfinedTestDispatcher()
    ) {
        coEvery { getCountriesUseCase() } returns Result.success(emptyList())

        viewModel.loadCountries()

        val state = viewModel.uiState.first()
        assert(state is CountryUiState.Empty)
    }

    @After
    fun dispose(){
        Dispatchers.resetMain()
    }
}