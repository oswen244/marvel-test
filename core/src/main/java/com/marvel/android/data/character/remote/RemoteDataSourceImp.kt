package com.marvel.android.data.character.remote

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import java.lang.Exception

class RemoteDataSourceImp(private val service: ServiceCharacter, private val apiKey: String): RemoteDataSource {
    override suspend fun getCharacters(
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String
    ): OperationResult<BaseModelResponse> {
        val result = service.getCharacters(apiKey, ts, hash, limit, offset)
        result.let {
            return if(it.isSuccessful && it.body() != null){
                OperationResult.Success(it.body())
            }else{
                OperationResult.Error(Exception(it.body()?.status))
            }
        }
    }

    override suspend fun getCharacterComics(
        id: Int,
        limit: Int?,
        offset: Int?,
        ts: Long,
        hash: String
    ): OperationResult<BaseModelResponse> {
        service.getCharacterComics(id, apiKey, ts, hash, limit, offset).let {
            return if(it.isSuccessful && it.body() != null){
                OperationResult.Success(it.body())
            }else{
                OperationResult.Error(Exception(it.body()?.status))
            }
        }
    }
}