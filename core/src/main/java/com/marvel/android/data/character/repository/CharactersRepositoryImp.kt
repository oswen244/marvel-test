package com.marvel.android.data.character.repository

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.base.Utils
import com.marvel.android.data.character.local.LocalDataSource
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.data.character.remote.RemoteDataSource

class CharactersRepositoryImp(private val remoteDataSource: RemoteDataSource,
                              private val localDataSource: LocalDataSource):
    CharactersRepository {
    override suspend fun getCharacters(
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String,
        isNetWorkAvailable: Boolean
    ): OperationResult<BaseModelResponse> {
        if(isNetWorkAvailable){
            val result = remoteDataSource.getCharacters(limit, offset, ts, hash)
            if(result is OperationResult.Success){
                result.data?.let { localDataSource.saveCharacters(it) }
                return result
            }
        }
        return localDataSource.getCharacters()
    }

    override suspend fun getCharacterComics(
        id: Int,
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String,
        isNetWorkAvailable: Boolean
    ): OperationResult<BaseModelResponse> {
        var result = localDataSource.getCharacterComics(id)
        if(result is OperationResult.Error && isNetWorkAvailable){
            result = remoteDataSource.getCharacterComics(id, limit, offset, ts, hash)
            if(result is OperationResult.Success){
                result.data?.let { localDataSource.saveCharacterComics(id.toString(), it) }
                return result
            }
        }
        return result
    }
}