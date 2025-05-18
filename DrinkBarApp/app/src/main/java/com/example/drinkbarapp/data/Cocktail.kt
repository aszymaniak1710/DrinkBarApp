package com.example.drinkbarapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cocktail(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String,
    val ingredients: String,
    val recipe: String,
    val imageRes: Int
)
