package com.marvel.android.data.character.remote

import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import java.lang.Exception

class RemoteDataSourceImp(private val service: ServiceCharacter): RemoteDataSource {
    override suspend fun getCharacters(): OperationResult<List<CharacterEntity>> {
        val result = service.getCharacters()
        result.let {
            return if(it.isSuccessful && it.body() != null){
                OperationResult.Success(it.body()!!.results)
            }else{
                OperationResult.Error(Exception("Error"))
            }
        }
    }
}