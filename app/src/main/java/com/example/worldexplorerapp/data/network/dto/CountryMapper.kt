package com.example.worldexplorer.data.dto

import com.example.worldexplorerapp.model.Country


fun CountryDto.toDomain(): Country {
    return Country(
        name = name,
        region = region,
        code = code,
        capital = capital
    )
}