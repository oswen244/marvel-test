package com.marvel.android.di

import com.marvel.android.BuildConfig
import org.koin.dsl.module

val hashKeyModule = module {
    factory { BuildConfig.publicKey }
}