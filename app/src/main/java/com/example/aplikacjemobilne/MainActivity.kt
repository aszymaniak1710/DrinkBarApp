package com.example.aplikacjemobilne

import android.content.Intent
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
    private var selectedCocktail: Cocktail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isTablet = findViewById<View?>(R.id.details_container) != null

        if (savedInstanceState != null) {
            selectedCocktail = savedInstanceState.getParcelable("selected_cocktail", Cocktail::class.java)

            if (isTablet) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CocktailListFragment())
                    .commit()

                selectedCocktail?.let {
                    showCocktailDetailsFragment(it)
                }
            }

        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CocktailListFragment())
                .commit()
        }
    }

    override fun onCocktailSelected(cocktail: Cocktail) {
        selectedCocktail = cocktail
        showCocktailDetailsFragment(cocktail)
    }

    private fun showCocktailDetailsFragment(cocktail: Cocktail) {
        if (isTablet) {
            val fragment = CocktailDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("name", cocktail.name)
                    putString("ingredients", cocktail.ingredients)
                    putString("recipe", cocktail.recipe)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.details_container, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("name", cocktail.name)
                putExtra("ingredients", cocktail.ingredients)
                putExtra("recipe", cocktail.recipe)
            }
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectedCocktail?.let {
            outState.putParcelable("selected_cocktail", it)
        }
    }
}

