package com.varma.hemanshu.botsup

import android.app.Application
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Avoiding logs in Production
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}