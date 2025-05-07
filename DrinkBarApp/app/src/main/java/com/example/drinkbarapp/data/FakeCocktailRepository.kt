package com.example.drinkbarapp.data

import com.example.drinkbarapp.R
import com.example.drinkbarapp.model.Cocktail

object FakeCocktailRepository {
    private val cocktailList = listOf(
        Cocktail(1, "Mojito", "Długie", "Rum, Limonka, Mięta, Cukier, Woda gazowana", "ZZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij Zmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij Zmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij Zmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij mieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowanąZmieszaj składniki, dodaj lód, uzupełnij wodą gazowaną", R.drawable.mojito),
        Cocktail(2, "Martini", "Długie", "Gin, Wermut", "Wymieszaj i podaj schłodzone", R.drawable.martini),
        Cocktail(3, "Mai Tai", "Długie", "Rum jasny, Rum ciemny, Curaçao, Sok limonkowy, Syrop migdałowy", "Wstrząśnij i podaj z lodem", R.drawable.maitai),
        Cocktail(4, "Pina Colada", "Długie", "Rum, Mleczko kokosowe, Sok ananasowy", "Zmiksuj wszystkie składniki z lodem", R.drawable.pinacolada),
        Cocktail(5, "Tequila Sunrise", "Długie", "Tequila, Sok pomarańczowy, Grenadyna", "Wlej grenadynę na końcu, aby uzyskać efekt warstwowy", R.drawable.tequilasunrise),
        Cocktail(6, "Bloody Mary", "Długie", "Wódka, Sok pomidorowy, Sok cytrynowy, Przyprawy", "Wymieszaj i podaj z selerem", R.drawable.bloodymary),
        Cocktail(7, "Sex on the Beach", "Długie", "Wódka, Likier brzoskwiniowy, Sok pomarańczowy, Sok żurawinowy", "Wstrząśnij z lodem i podaj", R.drawable.sexonthebeach),
        Cocktail(8, "Blue Lagoon", "Długie", "Wódka, Blue Curaçao, Sprite", "Wymieszaj w szklance z lodem", R.drawable.bluelagoon),

        Cocktail(9, "Margarita", "Szybkie", "Tequila, Triple Sec, Limonka", "Podaj z solą na brzegu szkła", R.drawable.margarita),
        Cocktail(10, "Negroni", "Szybkie", "Gin, Campari, Wermut czerwony", "Wymieszaj z lodem i podaj", R.drawable.negroni),
        Cocktail(11, "Old Fashioned", "Szybkie", "Bourbon, Cukier, Bitters, Woda", "Wymieszaj i dodaj dużą kostkę lodu", R.drawable.oldfashioned),
        Cocktail(12, "Whiskey Sour", "Szybkie", "Whiskey, Sok cytrynowy, Cukier, Białko jaja", "Wstrząśnij i podaj z lodem", R.drawable.whiskeysour),
        Cocktail(13, "Espresso Martini", "Szybkie", "Wódka, Likier kawowy, Espresso", "Wstrząśnij i podaj bez lodu", R.drawable.espressomartini),
        Cocktail(14, "White Russian", "Szybkie", "Wódka, Likier kawowy, Śmietanka", "Wlej warstwowo do szklanki z lodem", R.drawable.whiterussian),
        Cocktail(15, "Shot B52", "Szybkie", "Kahlua, Baileys, Cointreau", "Wlej warstwowo do kieliszka", R.drawable.shotb52),
        Cocktail(16, "Sidecar", "Szybkie", "Cognac, Triple Sec, Sok cytrynowy", "Wstrząśnij z lodem i podaj w kieliszku koktajlowym", R.drawable.sidecar)
    )

    fun getCocktailsByCategory(category: String): List<Cocktail> {
        return cocktailList.filter { it.category == category }
    }

    fun getCocktailDetails(id: Int): Cocktail? {
        return cocktailList.find { it.id == id }
    }

    fun getAllCocktails(): List<Cocktail> = cocktailList
}