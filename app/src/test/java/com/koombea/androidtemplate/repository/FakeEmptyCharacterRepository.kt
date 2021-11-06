package com.koombea.androidtemplate.repository

import com.marvel.android.base.BaseDataModel
import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.repository.CharactersRepository
import java.lang.Exception

class FakeEmptyCharacterRepository: CharactersRepository {
    override suspend fun getCharacters(
        name: String?,
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String,
        isNetWorkAvailable: Boolean
    ): OperationResult<BaseModelResponse> {
        val response = BaseModelResponse(0, "status", BaseDataModel(
            offset = 0,
            limit = 0,
            total = 100,
            count = 100,
            emptyList()
        ))
        return OperationResult.Success(response)
    }

    override suspend fun getCharacterComics(
        id: Int,
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String,
        isNetWorkAvailable: Boolean
    ): OperationResult<BaseModelResponse> {
        return OperationResult.Error(Exception("Error"))
    }
}