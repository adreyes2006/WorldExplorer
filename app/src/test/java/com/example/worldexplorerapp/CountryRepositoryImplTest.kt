package com.example.worldexplorerapp

import com.example.worldexplorer.data.dto.CountryDto
import com.example.worldexplorer.network.ApiService
import com.example.worldexplorer.data.repository.CountryRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.collections.first

@OptIn(ExperimentalCoroutinesApi::class)
class CountryRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: CountryRepositoryImpl

    @Before
    fun setup() {
        apiService = mockk()
        repository = CountryRepositoryImpl(apiService)
    }

    @Test
    fun `getCountries returns success with mapped data`() = runTest {

        val dtoList = listOf(
            CountryDto(
                name = "Afghanistan",
                code = "AF",
                capital = "Kabul",
                region = "AS"
            )
        )
        coEvery { apiService.getCountries() } returns dtoList
        val result = repository.getCountries()
        assertTrue(result.isSuccess)
        val countries = result.getOrNull()
        assertNotNull(countries)
        assertEquals(1, countries?.size)
        assertEquals("Afghanistan", countries?.first()?.name)
        assertEquals("AF", countries?.first()?.code)
    }

    @Test
    fun `getCountries returns success with empty list`() = runTest {

        coEvery { apiService.getCountries() } returns emptyList()
        val result = repository.getCountries()

        assertTrue(result.isSuccess)
        val countries = result.getOrNull()
        assertNotNull(countries)
        assertTrue(countries!!.isEmpty())
    }

    @Test
    fun `getCountries returns failure when exception is thrown`() = runTest {

        val exception = kotlin.RuntimeException("Network error")
        coEvery { apiService.getCountries() } throws exception

        val result = repository.getCountries()

        assertTrue(result.isFailure)
        val thrown = result.exceptionOrNull()
        assertNotNull(thrown)
        assertEquals("Network error", thrown?.message)
    }
}