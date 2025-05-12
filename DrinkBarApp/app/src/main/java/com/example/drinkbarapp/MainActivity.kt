package com.example.drinkbarapp

import com.example.drinkbarapp.viewModel.CocktailViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            var isDarkTheme by remember { mutableStateOf(true) }

            DrinkBarAppTheme (darkTheme = isDarkTheme) {
                  App(
                    cocktailViewModel = cocktailViewModel,
                    timerViewModel = timerViewModel,
                      onToggleTheme = { isDarkTheme = !isDarkTheme }
                  )
            }
        }
    }
}

