package com.example.drinkbarapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.drinkbarapp.data.Cocktail
import com.example.drinkbarapp.data.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

class CocktailViewModel(private val repository: CocktailRepository) : ViewModel() {

    fun getCocktailsByCategory(category: String): Flow<List<Cocktail>> =
        repository.getCocktailsByCategory(category)

    fun getCocktailById(id: Int): Flow<Cocktail> =
        repository.getCocktailById(id)

    private val _selectedCocktail = MutableStateFlow<Cocktail?>(null)
    val selectedCocktail: StateFlow<Cocktail?> = _selectedCocktail

    fun selectCocktail(cocktail: Cocktail) {
        _selectedCocktail.value = cocktail
    }

    private val _searchResults = MutableStateFlow<List<Cocktail>>(emptyList())
    val searchResults: StateFlow<List<Cocktail>> = _searchResults.asStateFlow()

    fun searchCocktails(query: String) {
        viewModelScope.launch {
            repository.searchCocktails(query)
                .collectLatest { _searchResults.value = it }
        }
    }
}
