package com.example.drinkbarapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drinkbarapp.data.CocktailRepository

class CocktailViewModelFactory(private val repository: CocktailRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CocktailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}