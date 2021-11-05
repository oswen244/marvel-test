package com.marvel.android.data.character.repository

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity

interface CharactersRepository {
    suspend fun getCharacters(name: String?, limit: Int?, offset: Int?, ts: Long, hash: String, isNetWorkAvailable: Boolean): OperationResult<BaseModelResponse>
    suspend fun getCharacterComics(id: Int, limit: Int?, offset: Int?, ts: Long, hash: String, isNetWorkAvailable: Boolean): OperationResult<BaseModelResponse>
}