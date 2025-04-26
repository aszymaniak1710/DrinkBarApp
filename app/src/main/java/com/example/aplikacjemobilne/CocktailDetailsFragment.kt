package com.example.aplikacjemobilne

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
import android.util.Log

class CocktailDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cocktail_details, container, false)

        val name = arguments?.getString("name")
        val ingredients = arguments?.getString("ingredients")
        val recipe = arguments?.getString("recipe")

        val nameView = view.findViewById<TextView>(R.id.name)
        val ingredientsView = view.findViewById<TextView>(R.id.ingredients)
        val recipeView = view.findViewById<TextView>(R.id.recipe)

        nameView.text = name
        ingredientsView.text = "Składniki: $ingredients"
        recipeView.text = "Przepis: $recipe"

        childFragmentManager.beginTransaction()
            .replace(R.id.timer_container, TimerFragment())
            .commit()

        return view
    }
}
