package github.user.sdk.ui

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        intance = this
        startKoin {
            androidContext(this@MainApplication)
            github.user.sdk.di.AppModule().run {
                val list = listOf(
                    appModule,
                    vmModule,
                    repoModule
                )
                modules(list)
            }
        }
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        private lateinit var intance: MainApplication
        fun getApplication() = intance
    }
}