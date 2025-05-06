package com.example.drinkbarapp

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
        Spacer(modifier = Modifier.height(16.dp))
        MyTimer(viewModel = timerViewModel)
    }
}

@Composable
fun MyTimer(viewModel: TimerViewModel) {

    val timeLeft by viewModel.timeLeft.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    var inputTime by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = "Pozostały czas: $timeLeft")

        Row {
            IconButtonWithAction(
                onClick = { viewModel.startTimer() },
                icon = Icons.Default.PlayArrow,
                contentDescription = "Start",
                enabled = !isRunning
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButtonWithAction(
                onClick = { viewModel.stopTimer() },
                icon = Icons.Default.Close,
                contentDescription = "Stop",
                enabled = isRunning
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButtonWithAction(
                onClick = { viewModel.resetTimer() },
                icon = Icons.Default.Refresh,
                contentDescription = "Refresh"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {expanded = !expanded},
        ){
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Rozwiń/zwiń ustawienia",
                )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column {
                OutlinedTextField(
                    value = inputTime,
                    onValueChange = { inputTime = it },
                    label = { Text("Ustaw czas (sekundy)") },
                    singleLine = true,
                    enabled = !isRunning
                )
                Button(
                    onClick = {
                        val time = inputTime.toLongOrNull()
                        if (time != null && time > 0) {

                            viewModel.setInitialTime(time)
                        }
                    },
                    enabled = !isRunning
                ) {
                    Text("Ustaw czas")
                }
            }
        }
    }
}

@Composable
fun IconButtonWithAction(
    onClick : () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    enabled: Boolean = true
    ){
    Button(onClick = onClick, enabled = enabled){
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}

