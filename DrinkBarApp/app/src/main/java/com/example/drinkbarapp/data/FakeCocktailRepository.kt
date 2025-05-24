package com.example.drinkbarapp.data

import com.example.drinkbarapp.R
import com.example.drinkbarapp.data.Cocktail

object FakeCocktailRepository {
    private val cocktailList = listOf(
        Cocktail(1, "Mojito", "Długie", "Rum, Limonka, Mięta, Cukier, Woda gazowana", "1. Przygotuj wysoką szklankę typu highball i lekko ją schłodź (np. przez włożenie kilku kostek lodu na czas przygotowań). \n" +
                "\n" +
                "2. Limonkę przekrój na pół, jedną połówkę pokrój na 4–6 klinów i wrzuć je do szklanki. Przed wrzuceniem możesz lekko wycisnąć sok bezpośrednio do szklanki – wzmocni to smak.\n" +
                "\n" +
                "3. Do szklanki z limonką wsyp 2 łyżeczki cukru (najlepiej trzcinowego, ale może być też biały). Ewentualnie możesz użyć syropu cukrowego, jeśli chcesz szybszego rozpuszczenia.\n" +
                "\n" +
                "4. Użyj muddlera (ugniatacza) lub końcówki drewnianej łyżki, aby delikatnie ugnieść limonkę z cukrem. Celem jest wyciśnięcie soku i olejków ze skórki, ale bez rozgniatania białej części (albedo), która dodaje goryczy.\n" +
                "\n" +
                "5. Dodaj 6–10 listków świeżej mięty. Nie siekaj ich ani nie rozgniataj mocno. Wystarczy, że uderzysz je lekko w dłoniach, żeby uwolnić olejki eteryczne, i wrzucisz do szklanki.\n" +
                "\n" +
                "6. Wlej 40–50 ml białego rumu. Najlepiej, jeśli jest to rum o lekkim, neutralnym profilu – sprawdzi się Havana Club 3 Años, Bacardi, itp.\n" +
                "\n" +
                "7. Wsyp dużą ilość kruszonego lodu – najlepiej do około 3/4 wysokości szklanki. Kruszony lód lepiej chłodzi i szybciej miesza się ze składnikami, ale jeśli nie masz, możesz użyć lodu w kostkach.\n" +
                "\n" +
                "8. Wymieszaj wszystko dokładnie przy pomocy długiej łyżki barmańskiej (lub zwykłej). Mieszaj od dołu ku górze, aby równomiernie rozprowadzić smak limonki, cukru, rumu i mięty.\n" +
                "\n" +
                "9. Dopełnij szklankę dobrze schłodzoną wodą gazowaną (około 80–100 ml). Nie mieszaj zbyt intensywnie po dolaniu wody, żeby nie wypuścić bąbelków.\n" +
                "\n" +
                "10. Na koniec udekoruj koktajl gałązką mięty (lekko uderzoną w dłoniach) i ewentualnie plasterkiem limonki. Włóż słomkę i serwuj od razu, gdy koktajl jest mocno schłodzony.\n" +
                "\n" +
                "Uwagi: Nie ugniataj mięty zbyt mocno, bo drink stanie się gorzki. Nie używaj soku z limonki z butelki – świeża limonka to podstawa. Duża ilość lodu zapobiega rozwodnieniu drinka. Prawidłowo przygotowane Mojito powinno być zbalansowane: orzeźwiające, lekko kwaśne, z wyczuwalnym rumem, mięta powinna dominować w aromacie, a nie w smaku.\n", R.drawable.mojito),
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