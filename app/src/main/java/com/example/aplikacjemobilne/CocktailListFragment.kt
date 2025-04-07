package com.example.aplikacjemobilne

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
import android.util.Log

class CocktailListFragment : Fragment() {
    private val TAG = "CocktailListFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cocktail_list, container, false)

        val listView = view.findViewById<ListView>(R.id.cocktail_list)

        val cocktailList = listOf(
            Cocktail(
                name = "Mojito",
                ingredients = "Rum, Mięta, Cukier, Limonka, Woda gazowana",
                recipe = "Zgnieć miętę z cukrem i limonką, dodaj rum i wodę gazowaną, wymieszaj."
            ),
            Cocktail(
                name = "Pina Colada",
                ingredients = "Rum, Mleko kokosowe, Sok ananasowy",
                recipe = "Zblenduj wszystkie składniki z lodem do uzyskania kremowej konsystencji."
            ),
            Cocktail(
                name = "Negroni",
                ingredients = "Gin, Campari, Słodki wermut",
                recipe = "Wymieszaj składniki z lodem i podawaj z plasterkiem pomarańczy."
            )
        )

        val cocktailNames = cocktailList.map { it.name }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cocktailNames)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val cocktail = cocktailList[position]

            // Logujemy, aby sprawdzić, który koktajl został wybrany
            Log.d(TAG, "Selected cocktail: ${cocktail.name}")

            val intent = Intent(requireContext(), DetailsActivity::class.java)
            intent.putExtra("name", cocktail.name)
            intent.putExtra("ingredients", cocktail.ingredients)
            intent.putExtra("recipe", cocktail.recipe)

            // Logujemy dane, które są przekazywane do DetailsActivity
            Log.d(TAG, "Sending to DetailsActivity: Name = ${cocktail.name}, Ingredients = ${cocktail.ingredients}, Recipe = ${cocktail.recipe}")

            startActivity(intent)
        }

        return view
    }
}
