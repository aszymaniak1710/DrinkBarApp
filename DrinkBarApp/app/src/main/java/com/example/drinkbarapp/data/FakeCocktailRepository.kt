package com.example.drinkbarapp.data

import com.example.drinkbarapp.model.Cocktail

object FakeCocktailRepository {
    private val cocktailList = listOf(
        Cocktail(1, "Mojito", "long", "Rum, Limonka, Mięta", "Zmieszaj i podawaj z lodem"),
        Cocktail(2, "Martini", "long", "Gin, Wermut", "Wymieszaj i podaj schłodzone"),
        Cocktail(3, "Margarita", "short","Tequila, Triple Sec, Limonka", "Podaj z solą na brzegu szkła")
    )

    fun getCocktailsByCategory(category: String): List<Cocktail> {
        return cocktailList.filter { it.category == category }
    }

    fun getCocktailDetails(id: Int): Cocktail? {
        return cocktailList.find { it.id == id }
    }

    fun getAllCocktails(): List<Cocktail> = cocktailList
}