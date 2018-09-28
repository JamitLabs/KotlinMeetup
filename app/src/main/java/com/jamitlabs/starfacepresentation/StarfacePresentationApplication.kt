package com.jamitlabs.starfacepresentation

import android.app.Application
import com.jamitlabs.starfacepresentation.di.appModule
import com.jamitlabs.starfacepresentation.di.viewModelModule
import org.koin.android.ext.android.startKoin
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

        startKoin(this, listOf(appModule, viewModelModule))
    }
}
