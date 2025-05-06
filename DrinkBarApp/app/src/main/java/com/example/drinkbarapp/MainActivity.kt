package com.example.drinkbarapp

import com.example.drinkbarapp.viewModel.CocktailViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.drinkbarapp.ui.screens.App
import com.example.drinkbarapp.ui.theme.DrinkBarAppTheme
import com.example.drinkbarapp.viewModel.TimerViewModel

class MainActivity : ComponentActivity() {

    private val cocktailViewModel: CocktailViewModel by viewModels()
    private val timerViewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrinkBarAppTheme {
                  App(
                    cocktailViewModel = cocktailViewModel,
                    timerViewModel = timerViewModel
                  )
            }
        }
    }
}

