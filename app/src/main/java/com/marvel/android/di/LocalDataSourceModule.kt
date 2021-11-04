package com.marvel.android.di

import com.marvel.android.base.ApplicationClass
import com.marvel.android.data.character.local.LocalDataSource
import com.marvel.android.data.character.local.LocalDataSourceImp
import org.koin.dsl.module

val localDataSourceModule = module {
    single<LocalDataSource> { LocalDataSourceImp(ApplicationClass.prefs) }
}