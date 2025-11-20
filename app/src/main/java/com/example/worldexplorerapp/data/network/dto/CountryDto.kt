package com.example.worldexplorer.data.dto

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("capital")
    val capital: String
)