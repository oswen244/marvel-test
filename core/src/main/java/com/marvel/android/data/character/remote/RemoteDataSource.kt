package com.marvel.android.data.character.remote

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity

interface RemoteDataSource {
    suspend fun getCharacters(limit: Int?, offset: Int?, ts: Long, hash: String): OperationResult<BaseModelResponse>
    suspend fun getCharacterComics(id: Int, limit: Int?, offset: Int?, ts: Long, hash: String): OperationResult<BaseModelResponse>
}