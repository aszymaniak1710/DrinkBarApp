package com.example.drinkbarapp

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drinkbarapp.ui.theme.DrinkBarAppTheme

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cocktailName = intent.getStringExtra("cocktail_name") ?: "Brak nazwy"
        val cocktailIngredients = intent.getStringExtra("cocktail_ingredients") ?: "Brak składników"
        val cocktailRecipe = intent.getStringExtra("cocktail_recipe") ?: "Brak przepisu"
        val timerViewModel: TimerViewModel by viewModels()


        setContent {
            DrinkBarAppTheme {
                CocktailDetail(
                    cocktail = Cocktail(
                        id = 0,
                        name = cocktailName,
                        ingredients = cocktailIngredients,
                        recipe = cocktailRecipe
                    ),
                    timerViewModel = timerViewModel
                )
            }
        }
    }
}

@Composable
fun CocktailDetail(cocktail: Cocktail, modifier: Modifier = Modifier, timerViewModel : TimerViewModel) {
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
        MyTimer(viewModel = timerViewModel)
    }
}

@Composable
fun MyTimer(viewModel: TimerViewModel) {

    val timeLeft by viewModel.timeLeft.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()

    Column {
        Text(text = "Pozostały czas: $timeLeft")

        Row {
            Button(onClick = { viewModel.startTimer() }, enabled = !isRunning) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.stopTimer() }, enabled = isRunning) {
                Text("Stop")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.resetTimer() }) {
                Text("Reset")
            }
        }
    }
}