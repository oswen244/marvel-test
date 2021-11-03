package com.marvel.android.data.character.repository

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.data.character.remote.RemoteDataSource

class CharactersRepositoryImp(private val remoteDataSource: RemoteDataSource):
    CharactersRepository {
    override suspend fun getCharacters(
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String
    ): OperationResult<BaseModelResponse> {
        return remoteDataSource.getCharacters(limit, offset, ts, hash)
    }

    override suspend fun getCharacterComics(
        id: Int,
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String
    ): OperationResult<BaseModelResponse> {
        return remoteDataSource.getCharacterComics(id, limit, offset, ts, hash)
    }

}