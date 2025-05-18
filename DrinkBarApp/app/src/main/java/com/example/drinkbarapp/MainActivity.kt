package com.example.drinkbarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drinkbarapp.data.AppDatabase
import com.example.drinkbarapp.data.Cocktail
import com.example.drinkbarapp.data.CocktailRepository
import com.example.drinkbarapp.data.FakeCocktailRepository
import com.example.drinkbarapp.ui.screens.App
import com.example.drinkbarapp.ui.theme.DrinkBarAppTheme
import com.example.drinkbarapp.viewModel.CocktailViewModel
import com.example.drinkbarapp.viewModel.CocktailViewModelFactory
import com.example.drinkbarapp.viewModel.TimerViewModel
import kotlinx.coroutines.launch
import java.util.Arrays.asList

class MainActivity : ComponentActivity() {

    private val timerViewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = CocktailRepository(database.cocktailDao())
        val viewModelFactory = CocktailViewModelFactory(repository)

//        lifecycleScope.launch {
//            repository.insertCocktails(FakeCocktailRepository.getAllCocktails())
//        }

        enableEdgeToEdge()
        setContent {
            val viewModel: CocktailViewModel = viewModel(factory = viewModelFactory)

            var isDarkTheme by remember { mutableStateOf(true) }

            DrinkBarAppTheme (darkTheme = isDarkTheme) {
                  App(
                      cocktailViewModel = viewModel,
                      timerViewModel = timerViewModel,
                      onToggleTheme = { isDarkTheme = !isDarkTheme }
                  )
            }

        }
    }
}


