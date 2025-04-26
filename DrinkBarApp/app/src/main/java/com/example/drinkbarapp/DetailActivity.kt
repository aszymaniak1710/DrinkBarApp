package com.example.drinkbarapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drinkbarapp.ui.theme.DrinkBarAppTheme

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cocktailName = intent.getStringExtra("cocktail_name") ?: "Brak nazwy"
        val cocktailIngredients = intent.getStringExtra("cocktail_ingredients") ?: "Brak składników"
        val cocktailRecipe = intent.getStringExtra("cocktail_recipe") ?: "Brak przepisu"
        Log.d("DetailActivity", "Selected cocktail: ${cocktailName}")

        setContent {
            DrinkBarAppTheme {
                CocktailDetail(
                    cocktail = Cocktail(
                        id = 0,
                        name = cocktailName,
                        ingredients = cocktailIngredients,
                        recipe = cocktailRecipe
                    )
                )
            }
        }
    }
}

@Composable
fun CocktailDetail(cocktail: Cocktail, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = cocktail.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Składniki:", style = MaterialTheme.typography.titleMedium)
        Text(text = cocktail.ingredients, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Przepis:", style = MaterialTheme.typography.titleMedium)
        Text(text = cocktail.recipe, style = MaterialTheme.typography.bodyLarge)
    }
}
