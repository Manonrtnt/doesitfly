package com.example.doesitfly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.doesitfly.databinding.ActivityMainBinding
import com.example.doesitfly.databinding.FragmentListBinding
import com.example.doesitfly.viewModel.ListFlyingSiteViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val viewModel by lazy { ViewModelProvider(this)[ListFlyingSiteViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loadData()

        viewModel.data.value

        val navigation = findViewById<BottomNavigationView>(R.id.navigationBar)
        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tab_item_list -> {
                    loadFragment(ListFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab_item_map -> {
                    loadFragment(MapFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment : Fragment){
        // injecté fragment container
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_fragment, fragment)
        // retirer backStack
        transaction.addToBackStack(null)
        transaction.commit()
    }
}