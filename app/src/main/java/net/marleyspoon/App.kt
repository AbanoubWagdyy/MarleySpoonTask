package net.marleyspoon

import android.app.Application
import com.contentful.java.cda.CDAArray
import net.marleyspoon.di.repositoryModule
import net.marleyspoon.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    internal lateinit var result: CDAArray

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App.applicationContext)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}