package com.varma.hemanshu.botsup

import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.varma.hemanshu.botsup.databinding.ActivityMainBinding
import com.varma.hemanshu.botsup.utils.CommonUtils
import com.varma.hemanshu.botsup.utils.SharedPrefUtils
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: MaterialToolbar
    private lateinit var prefInstance: SharedPrefUtils

    override fun onCreate(savedInstanceState: Bundle?) {

        //Setting theme to display splash when cold boot
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        //Binding Layout
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        prefInstance = SharedPrefUtils.getInstance(applicationContext)
        //Checking for First Launch
        isFirstLaunch()

        //Setting up material toolbar
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        binding.appBarLayout.bringToFront()

        //Binding Toolbar and Navigation Graph to sync
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, null)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
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

    /**
     * Checks if it is first launch
     * if yes, then create channel(s) for notification
     */
    private fun isFirstLaunch() {
        val isFirstLaunch = prefInstance.getBooleanValue(Constants.PREF_IS_FIRST_LAUNCH)
        Timber.i("First Launch ${!isFirstLaunch}")
        if (!isFirstLaunch) {

            //Channel is required for Android Oreo and above
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //Local Channel
                CommonUtils.createChannel(
                    applicationContext,
                    getString(R.string.local_notification_channel_id),
                    getString(R.string.local_notification_channel_name),
                    getString(R.string.local_notification_channel_description),
                    NotificationManager.IMPORTANCE_HIGH
                )

                //FCM Channel with high importance
                CommonUtils.createChannel(
                    applicationContext,
                    getString(R.string.fcm_notification_channel_id),
                    getString(R.string.fcm_notification_channel_name),
                    getString(R.string.fcm_notification_channel_description),
                    NotificationManager.IMPORTANCE_HIGH
                )
            }
            prefInstance.setBooleanValue(Constants.PREF_IS_FIRST_LAUNCH, true)
        }
    }

    //Handled when up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
