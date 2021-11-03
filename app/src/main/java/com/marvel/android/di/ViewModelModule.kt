package com.marvel.android.di

import com.marvel.android.ui.character.CharacterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { CharacterViewModel(getCharactersUseCase = get(), getCharacterComicsUseCase = get()) }
}
