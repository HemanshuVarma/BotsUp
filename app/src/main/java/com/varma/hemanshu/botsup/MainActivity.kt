package com.varma.hemanshu.botsup

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.varma.hemanshu.botsup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding Layout
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Setting up material toolbar
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        binding.appBarLayout.bringToFront()

        //Binding Toolbar and Navigation Graph to sync
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, null)

        setUpNavigationListener(navController)
    }

    //Hiding Appbar when navigating to About Fragment
    private fun setUpNavigationListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.aboutFragment) {
                binding.appBarLayout.visibility = View.GONE
            } else
                binding.appBarLayout.visibility = View.VISIBLE
        }
    }

    //Handled when up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
