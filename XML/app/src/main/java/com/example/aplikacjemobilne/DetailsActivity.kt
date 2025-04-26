package com.example.aplikacjemobilne

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.util.Log
import android.widget.Button

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val name = intent.getStringExtra("name")
        val ingredients = intent.getStringExtra("ingredients")
        val recipe = intent.getStringExtra("recipe")

        val fragment = CocktailDetailsFragment()
        val args = Bundle()
        args.putString("name", name)
        args.putString("ingredients", ingredients)
        args.putString("recipe", recipe)
        fragment.arguments = args

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
