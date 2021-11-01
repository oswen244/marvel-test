package com.marvel.android.di

import com.marvel.android.data.character.remote.RemoteDataSource
import com.marvel.android.data.character.remote.RemoteDataSourceImp
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImp(service = get()) }
}
