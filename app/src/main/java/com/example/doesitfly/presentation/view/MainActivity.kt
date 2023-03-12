package com.example.doesitfly.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.doesitfly.R
import com.example.doesitfly.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Charge le fragment MapFragment() par défaut
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout_fragment, MapFragment())
                .commit()
        }

        val navigation = findViewById<BottomNavigationView>(R.id.navigationBar)
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_item_list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout_fragment, ListFragment())
                        .commit()
                    true
                }
                R.id.tab_item_map -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout_fragment, MapFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}