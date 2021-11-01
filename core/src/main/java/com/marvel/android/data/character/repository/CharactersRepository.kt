package com.marvel.android.data.character.repository

import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity

interface CharactersRepository {
    suspend fun getCharacters(): OperationResult<List<CharacterEntity>>
}