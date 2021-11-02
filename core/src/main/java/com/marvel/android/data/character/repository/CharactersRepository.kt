package com.marvel.android.data.character.repository

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity

interface CharactersRepository {
    suspend fun getCharacters(limit: Int?, offset: Int?, ts: Long, hash: String): OperationResult<BaseModelResponse>
}