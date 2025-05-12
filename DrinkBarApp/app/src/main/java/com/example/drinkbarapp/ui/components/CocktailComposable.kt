package com.example.drinkbarapp.ui.components

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.drinkbarapp.viewModel.CocktailViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.drinkbarapp.data.FakeCocktailRepository
import com.example.drinkbarapp.viewModel.TimerViewModel
import com.example.drinkbarapp.model.Cocktail


@Composable
fun CocktailList(
    category: String,
    onCocktailSelected: (Cocktail) -> Unit,
    modifier: Modifier = Modifier
) {
    val cocktailsToDisplay = FakeCocktailRepository.getCocktailsByCategory(category)
    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$category w przygotowaniu drinki",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
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
                        .clickable { onCocktailSelected(cocktail) },
                            colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = cocktail.imageRes),
                            contentDescription = cocktail.name,
                            modifier = Modifier
                                .size(140.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = cocktail.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailDetailScaffold(
    displayBackButton: Boolean,
    cocktail: Cocktail,
    timerViewModel: TimerViewModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)
    val barHeight = TopAppBarDefaults.LargeAppBarExpandedHeight + 50.dp

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                expandedHeight = barHeight,
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = cocktail.name,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(70)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                },
                navigationIcon = {
                    if (displayBackButton) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = CircleShape
                                )
                                .size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Wróć"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent, scrolledContainerColor = Color.Transparent),
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val smsUri = Uri.parse("smsto:")
                    val intent = Intent(Intent.ACTION_SENDTO, smsUri).apply {
                        putExtra("sms_body", "Składniki koktajlu:\n${cocktail.ingredients}")
                    }

                    val packageManager = context.packageManager
                    if (intent.resolveActivity(packageManager) != null) {
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "Brak aplikacji do wysyłania SMS", Toast.LENGTH_LONG).show()
                    }
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Wyślij SMS")
            }
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight + 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(id = cocktail.imageRes),
                contentDescription = "Android logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.place(0, topAppBarState.heightOffset.toInt())
                        }
                    }
                    .fillMaxWidth()
            )
        }


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Składniki:", style = MaterialTheme.typography.titleMedium)
            Text(text = cocktail.ingredients, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Przepis:", style = MaterialTheme.typography.titleMedium)
            Text(text = cocktail.recipe, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            MyTimer(viewModel = timerViewModel)
        }
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