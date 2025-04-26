package com.example.drinkbarapp

import CocktailViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.drinkbarapp.ui.theme.DrinkBarAppTheme

class MainActivity : ComponentActivity() {

    private val cocktailList = listOf(
        Cocktail(1, "Mojito", "Rum, Limonka, Mięta", "Zmieszaj i podawaj z lodem"),
        Cocktail(2, "Martini", "Gin, Wermut", "Wymieszaj i podaj schłodzone"),
        Cocktail(3, "Margarita", "Tequila, Triple Sec, Limonka", "Podaj z solą na brzegu szkła")
    )

    private val cocktailViewModel: CocktailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrinkBarAppTheme {
                CocktailListScreen(
                    cocktails = cocktailList,
                    cocktailViewModel = cocktailViewModel) { selectedCocktail ->
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

data class Cocktail(
    val id: Int,
    val name: String,
    val ingredients: String,
    val recipe: String
)

@Composable
fun CocktailListScreen(
    cocktails: List<Cocktail>,
    cocktailViewModel: CocktailViewModel,
    onCocktailSelected: (Cocktail) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val selectedCocktail by cocktailViewModel.selectedCocktail.collectAsState()

    if (screenWidthDp < 600) {
        // Telefon -> tylko lista
        CocktailList(cocktails = cocktails, onCocktailSelected = onCocktailSelected)
    } else {
        // Tablet -> lista + szczegóły
        Row(Modifier.fillMaxSize()) {
            CocktailList(
                cocktails = cocktails,
                onCocktailSelected = { cocktail ->
                    cocktailViewModel.selectCocktail(cocktail)
                },
                modifier = Modifier.weight(1f)
            )
            selectedCocktail?.let {
                CocktailDetail(cocktail = it, modifier = Modifier.weight(1f))
            } ?: run {
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = "Wybierz koktajl", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun CocktailList(
    cocktails: List<Cocktail>,
    onCocktailSelected: (Cocktail) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(cocktails.size) { index ->
            val cocktail = cocktails[index]
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { onCocktailSelected(cocktail) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = cocktail.name, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}