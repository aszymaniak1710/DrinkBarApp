package com.example.drinkbarapp

import android.content.Intent
import com.example.drinkbarapp.viewModel.CocktailViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.drinkbarapp.data.FakeCocktailRepository
import com.example.drinkbarapp.ui.screens.HomeScreen
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
                  HomeScreen(cocktails = FakeCocktailRepository.getAllCocktails(),
                    cocktailViewModel = cocktailViewModel,
                    timerViewModel = timerViewModel) { selectedCocktail ->
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("cocktail_id", selectedCocktail.id)
                    intent.putExtra("cocktail_name", selectedCocktail.name)
                    intent.putExtra("cocktail_ingredients", selectedCocktail.ingredients)
                    intent.putExtra("cocktail_recipe", selectedCocktail.recipe)
                    startActivity(intent)
                  }
            }
        }
    }
}

