package com.marvel.android.di

import com.marvel.android.data.character.repository.CharactersRepository
import com.marvel.android.data.character.repository.CharactersRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single<CharactersRepository> { CharactersRepositoryImp(remoteDataSource = get(), localDataSource = get()) }
}