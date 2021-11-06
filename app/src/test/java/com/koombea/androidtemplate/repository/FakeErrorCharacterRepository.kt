package com.koombea.androidtemplate.repository

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.repository.CharactersRepository
import java.lang.Exception

class FakeErrorCharacterRepository: CharactersRepository {
    override suspend fun getCharacters(
        name: String?,
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String,
        isNetWorkAvailable: Boolean
    ): OperationResult<BaseModelResponse> {
        return OperationResult.Error(Exception("Error"))
    }

    override suspend fun getCharacterComics(
        id: Int,
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String,
        isNetWorkAvailable: Boolean
    ): OperationResult<BaseModelResponse> {
        return OperationResult.Error(null)
    }
}