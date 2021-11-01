package com.marvel.android.di

import com.marvel.android.ui.characterlist.CharacterListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { CharacterListViewModel(getCharactersUseCase = get()) }
}
