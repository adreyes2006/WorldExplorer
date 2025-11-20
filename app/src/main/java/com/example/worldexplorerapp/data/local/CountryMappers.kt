package com.example.worldexplorerapp.data.local

import com.example.worldexplorerapp.model.Country
import com.example.worldexplorerapp.data.local.entity.CountryEntity

fun Country.toEntitu() = CountryEntity(
    code = this.code,
    name = this.name,
    region = this.region,
    capital = this.capital
)
fun CountryEntity.toDomain() = Country(
    code = this.code,
    name = this.name,
    region = this.region,
    capital = this.capital
)