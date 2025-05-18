package com.example.drinkbarapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail WHERE category = :category")
    fun getCocktailsByCategory(category: String): Flow<List<Cocktail>>

    @Query("SELECT * FROM cocktail WHERE name LIKE '%' || :keyword || '%'")
    fun getCocktailsByKeyword(keyword: String): Flow<List<Cocktail>>

    @Query("SELECT * FROM cocktail WHERE id = :id")
    fun getCocktailById(id: Int): Flow<Cocktail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktails(cocktails: List<Cocktail>)

    @Query("SELECT * FROM cocktail WHERE LOWER(name) LIKE :query OR LOWER(ingredients) LIKE :query")
    fun searchCocktails(query: String): Flow<List<Cocktail>>

}