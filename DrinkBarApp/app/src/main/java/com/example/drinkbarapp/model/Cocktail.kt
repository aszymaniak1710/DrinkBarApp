package com.example.drinkbarapp.model

data class Cocktail(
    val id: Int,
    val name: String,
    val category: String,
    val ingredients: String,
    val recipe: String,
    val imageRes: Int
)