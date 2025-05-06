package com.example.drinkbarapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.drinkbarapp.model.Cocktail
import com.example.drinkbarapp.ui.components.CocktailScreen
import com.example.drinkbarapp.viewModel.CocktailViewModel
import com.example.drinkbarapp.viewModel.TimerViewModel

@Composable
fun HomeScreen(
    cocktails: List<Cocktail>,
    cocktailViewModel: CocktailViewModel,
    timerViewModel: TimerViewModel,
    onCocktailSelected: (Cocktail) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val coroutineScope = rememberCoroutineScope()
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        if (page == 0) {
            // strona początkowa
        } else if(page == 1) {
            CocktailScreen(
                    category = "short",
                    cocktails = cocktails,
                    cocktailViewModel = cocktailViewModel,
                    timerViewModel = timerViewModel,
                    onCocktailSelected = onCocktailSelected
            )
        }
    }
}