package com.marvel.android.base

import android.annotation.SuppressLint
import android.app.Application
import com.marvel.android.base.preferences.PreferencesImp
import com.marvel.android.di.*
import org.koin.core.context.startKoin

class ApplicationClass: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        var prefs: PreferencesImp? = null
        lateinit var instance: ApplicationClass
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = PreferencesImp(applicationContext)
        startKoin {
            modules(
                    listOf(
                            hashKeyModule,
                            networkModule,
                            localDataSourceModule,
                            dataSourceModule,
                            repositoryModule,
                            useCaseModule,
                            ViewModelModule
                    )
            )
        }
    }
}