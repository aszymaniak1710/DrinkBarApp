package com.example.drinkbarapp.data

import kotlinx.coroutines.flow.Flow

class CocktailRepository(private val cocktailDao: CocktailDao) {

    fun getCocktailsByCategory(category: String): Flow<List<Cocktail>> =
        cocktailDao.getCocktailsByCategory(category)

    fun getCocktailsByKeyword(keyword: String): Flow<List<Cocktail>> =
        cocktailDao.getCocktailsByKeyword(keyword)

    fun searchCocktails(query: String): Flow<List<Cocktail>> {
        return cocktailDao.searchCocktails("%${query.lowercase()}%")
    }

    fun getCocktailById(id: Int): Flow<Cocktail> =
        cocktailDao.getCocktailById(id)

    suspend fun insertCocktails(cocktails: List<Cocktail>) =
        cocktailDao.insertCocktails(cocktails)
}