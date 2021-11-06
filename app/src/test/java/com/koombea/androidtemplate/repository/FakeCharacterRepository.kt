package com.koombea.androidtemplate.repository

import com.marvel.android.base.BaseDataModel
import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.data.character.model.ThumbnailEntity
import com.marvel.android.data.character.repository.CharactersRepository
import com.marvel.android.data.comics.model.ComicURIEntity
import java.lang.Exception

class FakeCharacterRepository: CharactersRepository {
    override suspend fun getCharacters(
        name: String?,
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String,
        isNetWorkAvailable: Boolean
    ): OperationResult<BaseModelResponse> {
        val mockList: MutableList<CharacterEntity> = mutableListOf()
        mockList.add(
            CharacterEntity(0, null, "desc", ThumbnailEntity("url", "ext"), ComicURIEntity(
            "uri", 1)
            )
        )
        mockList.add(
            CharacterEntity(1, null, "desc", ThumbnailEntity("url", "ext"), ComicURIEntity(
            "uri", 1)
            )
        )
        mockList.add(
            CharacterEntity(2, null, "desc", ThumbnailEntity("url", "ext"), ComicURIEntity(
            "uri", 1)
            )
        )
        val response = BaseModelResponse(0, "status", BaseDataModel(
                offset = 0,
                limit = 0,
                total = 100,
                count = 100,
                mockList.toList()
            )
        )
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