package com.luigguidev.clothing_app.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.luigguidev.clothing_app.config.navigation.NavigationApp
import com.luigguidev.clothing_app.config.theme.ClothingappTheme
import com.luigguidev.clothing_app.viewmodel.CardScreenViewModel
import com.luigguidev.clothing_app.viewmodel.ClotheScreenViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clotheScreenViewModel by viewModels<ClotheScreenViewModel>()
        val cardScreenViewModel by viewModels<CardScreenViewModel>()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            val navController = rememberNavController()
            ClothingappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationApp(
                        clotheScreenViewModel = clotheScreenViewModel,
                        navController = navController,
                        cardScreenViewModel = cardScreenViewModel
                    )
                }
            }
        }
    }
}
