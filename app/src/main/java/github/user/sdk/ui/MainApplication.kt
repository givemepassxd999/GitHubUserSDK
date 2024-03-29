package github.user.sdk.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        intance = this
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        private lateinit var intance: MainApplication
        fun getApplication() = intance
    }
}