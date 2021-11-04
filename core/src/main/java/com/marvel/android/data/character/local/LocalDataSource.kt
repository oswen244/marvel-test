package com.marvel.android.data.character.local

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult

interface LocalDataSource {
    suspend fun getCharacters(): OperationResult<BaseModelResponse>
    suspend fun getCharacterComics(id: Int): OperationResult<BaseModelResponse>
    suspend fun saveCharacters(response: BaseModelResponse)
    suspend fun saveCharacterComics(id: String, response: BaseModelResponse)
}