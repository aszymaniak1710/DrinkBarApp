package com.example.drinkbarapp.ui.components

import android.annotation.SuppressLint
import com.example.drinkbarapp.viewModel.CocktailViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.drinkbarapp.data.FakeCocktailRepository
import com.example.drinkbarapp.viewModel.TimerViewModel
import com.example.drinkbarapp.model.Cocktail


@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CocktailScreen(
    category: String,
    cocktailViewModel: CocktailViewModel,
    timerViewModel: TimerViewModel,
    onCocktailSelected: (Cocktail) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val selectedCocktailState = cocktailViewModel.selectedCocktail.collectAsState()
    val selectedCocktail = selectedCocktailState.value

    val navController = rememberNavController()

    if (screenWidthDp < 600) {
        CocktailList(
            category = category,
            onCocktailSelected = onCocktailSelected
        )
    } else {
        Row(Modifier.fillMaxSize()) {
            CocktailList(
                category = category,
                onCocktailSelected = { cocktail ->
                    cocktailViewModel.selectCocktail(cocktail)
                },
                modifier = Modifier.weight(1f)
            )
            selectedCocktail?.let {
                CocktailDetail(cocktail = selectedCocktail, modifier = Modifier.weight(1f), timerViewModel = timerViewModel)
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
    category: String,
    onCocktailSelected: (Cocktail) -> Unit,
    modifier: Modifier = Modifier
) {
    val cocktailsToDisplay = FakeCocktailRepository.getCocktailsByCategory(category)
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 150.dp)
    ) {
        items(cocktailsToDisplay.size) { index ->
            val cocktail = cocktailsToDisplay[index]
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