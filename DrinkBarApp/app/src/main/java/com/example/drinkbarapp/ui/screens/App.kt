package com.example.drinkbarapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drinkbarapp.data.FakeCocktailRepository
import com.example.drinkbarapp.ui.components.CocktailDetailScaffold
import com.example.drinkbarapp.ui.components.CocktailScreen
import com.example.drinkbarapp.viewModel.CocktailViewModel
import com.example.drinkbarapp.viewModel.TimerViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    cocktailViewModel: CocktailViewModel,
    timerViewModel: TimerViewModel
) {
    val navController = rememberNavController()

    NavHost(
                navController = navController,
                startDestination = "main"
            ) {
                composable("main") {
                    PagerWithDrawerScreen(
                        navController = navController,
                        cocktailViewModel = cocktailViewModel,
                        timerViewModel = timerViewModel
                    )
                }
                composable("cocktail/{id}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                    val cocktail = id?.let { FakeCocktailRepository.getCocktailDetails(it) }
                    if (cocktail != null) {
                        CocktailDetailScaffold(
                            cocktail = cocktail,
                            timerViewModel = timerViewModel,
                            onBackClick = { navController.popBackStack() }
                        )
                    } else {
                        Text("Nie znaleziono koktajlu.")
                    }
                }
            }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagerWithDrawerScreen(
    navController: NavController,
    cocktailViewModel: CocktailViewModel,
    timerViewModel: TimerViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    val drawerItems = listOf(
        "Start" to 0,
        "Short drinks" to 1,
        "Long drinks" to 2
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Drink Bar",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
                drawerItems.forEach { (label, pageIndex) ->
                    NavigationDrawerItem(
                        label = { Text(label) },
                        selected = pagerState.currentPage == pageIndex,
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                                pagerState.animateScrollToPage(pageIndex)
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Drink Bar") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* TODO: wyszukiwanie */ }) {
                            Icon(Icons.Default.Search, contentDescription = "Szukaj")
                        }
                        IconButton(onClick = { /* TODO: więcej opcji */ }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Więcej opcji")
                        }
                    }
                )
            }
        ) { innerPadding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.padding(innerPadding)
            ) { page ->
                when (page) {
                    0 -> Text("Strona początkowa", modifier = Modifier.padding(32.dp))
                    1 -> CocktailScreen(
                        category = "short",
                        cocktailViewModel = cocktailViewModel,
                        timerViewModel = timerViewModel,
                        onCocktailSelected = { cocktail ->
                            navController.navigate("cocktail/${cocktail.id}")
                        }
                    )
                    2 -> CocktailScreen(
                        category = "long",
                        cocktailViewModel = cocktailViewModel,
                        timerViewModel = timerViewModel,
                        onCocktailSelected = { cocktail ->
                            navController.navigate("cocktail/${cocktail.id}")
                        }
                    )
                }
            }
        }
    }
}