package com.jamitlabs.starfacepresentation

import android.app.Application
import timber.log.Timber

class StarfacePresentationApplication : Application() {

    companion object {
        lateinit var INSTANCE: StarfacePresentationApplication
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        INSTANCE = this
    }

}
