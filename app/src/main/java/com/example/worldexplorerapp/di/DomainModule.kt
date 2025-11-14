package com.example.worldexplorerapp.di

import com.example.worldexplorer.data.repository.ICountryRepository
import com.example.worldexplorerapp.domain.GetCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    @ViewModelScoped
    fun provideGetCountryUseCase(
        countryRepository: ICountryRepository
    ): GetCountriesUseCase = GetCountriesUseCase(countryRepository)
}