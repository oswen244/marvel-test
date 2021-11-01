package com.marvel.android.data.character.remote

import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity

interface RemoteDataSource {
    suspend fun getCharacters(): OperationResult<List<CharacterEntity>>
}