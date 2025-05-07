package com.example.drinkbarapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drinkbarapp.data.FakeCocktailRepository
import com.example.drinkbarapp.model.Cocktail
import com.example.drinkbarapp.ui.components.CocktailDetailScaffold
import com.example.drinkbarapp.ui.components.CocktailList
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
                    MainScreen(
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
                            displayBackButton = true,
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

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    cocktailViewModel: CocktailViewModel,
    timerViewModel: TimerViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    val isTablet = screenWidthDp + screenHeightDp > 1500

    val drawerItems = listOf(
        "O aplikacji" to 0,
        "Szybkie drinki" to 1,
        "Dłuższe w przygotowaniu drinki" to 2
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
            val selectedCocktail by cocktailViewModel.selectedCocktail.collectAsState()

            if (!isTablet) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.padding(innerPadding)
                ) { page ->
                    when (page) {
                        0 -> Text("Strona początkowa", modifier = Modifier.padding(32.dp))
                        1 -> {
                            CocktailList(
                                category = "Szybkie",
                                onCocktailSelected = { cocktail ->
                                    navController.navigate("cocktail/${cocktail.id}")
                                }
                            )
                        }

                        2 -> CocktailList(
                            category = "Długie",
                            onCocktailSelected = { cocktail ->
                                navController.navigate("cocktail/${cocktail.id}")
                            }
                        )
                    }
                }
            }
            else {
                Row(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) { page ->
                        when (page) {
                            0 -> Text(
                                "Strona początkowa (tablet)",
                                modifier = Modifier.padding(32.dp)
                            )

                            1 -> CocktailList(
                                category = "Szybkie",
                                onCocktailSelected = { cocktail ->
                                    cocktailViewModel.selectCocktail(cocktail)
                                }
                            )

                            2 -> CocktailList(
                                category = "Długie",
                                onCocktailSelected = { cocktail ->
                                    cocktailViewModel.selectCocktail(cocktail)
                                }
                            )
                        }
                    }
                    selectedCocktail?.let {
                        CocktailDetailScaffold(
                            displayBackButton = false,
                            cocktail = it,
                            modifier = Modifier.weight(1f),
                            timerViewModel = timerViewModel
                        )
                    } ?: run {
                        Box(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Wybierz koktajl",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }


                }
            }
        }
    }
}