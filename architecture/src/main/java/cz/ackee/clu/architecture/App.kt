package cz.ackee.clu.architecture

import android.app.Application
import cz.ackee.clu.architecture.di.remoteModule
import cz.ackee.clu.architecture.di.repositoryModule
import cz.ackee.clu.architecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * App
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoryModule, remoteModule))
        }
    }
}