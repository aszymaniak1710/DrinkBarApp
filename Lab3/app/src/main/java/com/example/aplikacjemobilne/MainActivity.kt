package com.example.aplikacjemobilne

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.aplikacjemobilne.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CocktailSelectionListener {

    private var isTablet = false
    private lateinit var binding: ActivityMainBinding
    private var selectedCocktail: Cocktail? = null // Przechowywanie obiektu drinka

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CocktailListFragment())
            .commit()

        isTablet = findViewById<View?>(R.id.details_container) != null

        if (isTablet) {
            if (savedInstanceState != null) {
                selectedCocktail = savedInstanceState.getParcelable("selected_cocktail", Cocktail::class.java)
            }
            if(selectedCocktail != null){
                showCocktailDetailsFragment(selectedCocktail!!)
            } else {
                val cocktail = Cocktail("Wybierz drinka", "", "")
                showCocktailDetailsFragment(cocktail)
            }
        }
    }

    override fun onCocktailSelected(cocktail: Cocktail) {
        // Zapisujemy obiekt drinka
        selectedCocktail = cocktail
        showCocktailDetailsFragment(cocktail)
    }

    private fun showCocktailDetailsFragment(cocktail: Cocktail) {
        val fragment = CocktailDetailsFragment().apply {
            arguments = Bundle().apply {
                putString("name", cocktail.name)
                putString("ingredients", cocktail.ingredients)
                putString("recipe", cocktail.recipe)
            }
        }

        val containerId = if (isTablet) R.id.details_container else R.id.fragment_container

        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Zapisujemy obiekt drinka w stanie aktywności
        selectedCocktail?.let {
            outState.putParcelable("selected_cocktail", it)
        }
    }
}

