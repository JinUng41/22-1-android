package com.example.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.lab6.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appbarc: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nhf=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val topDest=setOf(R.id.homeFragment, R.id.page1Fragment, R.id.page2Fragment)
        appbarc= AppBarConfiguration(topDest, binding.drawerLayout)
        setupActionBarWithNavController(nhf.navController, appbarc)

        binding.navigationView.setupWithNavController(nhf.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragmentContainerView).navigateUp(appbarc)||super.onSupportNavigateUp()
    }




}

