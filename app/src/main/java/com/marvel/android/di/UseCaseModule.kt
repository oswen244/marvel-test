package com.marvel.android.di

import com.marvel.android.domain.GetCharactersUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCharactersUseCase(charactersRepository = get()) }
}