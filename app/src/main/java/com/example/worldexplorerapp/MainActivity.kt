package com.example.worldexplorerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.worldexplorerapp.ui.countries.CountryScreen
import com.example.worldexplorerapp.ui.theme.WorldExplorerAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorldExplorerAppTheme {
                CountryScreen()
            }
        }
    }
}
