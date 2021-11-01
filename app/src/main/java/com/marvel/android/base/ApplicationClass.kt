package com.marvel.android.base

import android.app.Application
import com.marvel.android.di.*
import org.koin.core.context.startKoin

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                    listOf(
                            networkModule,
                            dataSourceModule,
                            repositoryModule,
                            useCaseModule,
                            ViewModelModule
                    )
            )
        }
    }
}