package com.example.drinkbarapp

import com.example.drinkbarapp.viewModel.CocktailViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.drinkbarapp.ui.components.CocktailListScreen
import com.example.drinkbarapp.model.Cocktail
import com.example.drinkbarapp.ui.theme.DrinkBarAppTheme
import com.example.drinkbarapp.viewModel.TimerViewModel

class MainActivity : ComponentActivity() {

    private val cocktailList = listOf(
        Cocktail(1, "Mojito", "Rum, Limonka, Mięta", "Zmieszaj i podawaj z lodem"),
        Cocktail(2, "Martini", "Gin, Wermut", "Wymieszaj i podaj schłodzone"),
        Cocktail(3, "Margarita", "Tequila, Triple Sec, Limonka", "Podaj z solą na brzegu szkła")
    )

    private val cocktailViewModel: CocktailViewModel by viewModels()
    private val timerViewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrinkBarAppTheme {
                CocktailListScreen(
                    cocktails = cocktailList,
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

