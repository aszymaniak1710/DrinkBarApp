package com.example.drinkbarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.drinkbarapp.ui.components.CocktailDetail
import com.example.drinkbarapp.model.Cocktail
import com.example.drinkbarapp.ui.theme.DrinkBarAppTheme
import com.example.drinkbarapp.viewModel.TimerViewModel

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cocktailName = intent.getStringExtra("cocktail_name") ?: "Brak nazwy"
        val cocktailIngredients = intent.getStringExtra("cocktail_ingredients") ?: "Brak składników"
        val cocktailRecipe = intent.getStringExtra("cocktail_recipe") ?: "Brak przepisu"
        val timerViewModel: TimerViewModel by viewModels()


        setContent {
            DrinkBarAppTheme {
//                CocktailDetail(
//                    cocktail = Cocktail(
//                        id = 0,
//                        name = cocktailName,
//                        ingredients = cocktailIngredients,
//                        recipe = cocktailRecipe
//                    ),
//                    timerViewModel = timerViewModel
//                )
            }
        }
    }
}



