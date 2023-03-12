package com.example.doesitfly.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.doesitfly.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** Load the default [MapFragment]*/
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout_fragment, MapFragment())
                .commit()
        }

        /** Setup navigation bar [BottomNavigationView]*/
        val navigation = findViewById<BottomNavigationView>(R.id.navigationBar)
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_item_list -> {
                    /** Replace the current fragment with [ListFragment] when "List" tab is selected */
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout_fragment, ListFragment())
                        .commit()
                    true
                }
                R.id.tab_item_map -> {
                    /** Replace the current fragment with [MapFragment] when "Map" tab is selected */
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