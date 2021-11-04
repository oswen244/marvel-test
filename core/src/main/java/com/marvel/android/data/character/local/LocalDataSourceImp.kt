package com.marvel.android.data.character.local

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.Constants
import com.marvel.android.base.OperationResult
import com.marvel.android.base.preferences.PreferencesImp
import java.lang.Exception

class LocalDataSourceImp(private val preferences: PreferencesImp?): LocalDataSource {

    override suspend fun getCharacters(): OperationResult<BaseModelResponse> {
        val result = preferences?.get<BaseModelResponse>(Constants.CHARACTERS_KEY)
        if(result != null){
            return OperationResult.Success(result)
        }
        return OperationResult.Error(Exception("Empty Data"))
    }

    override suspend fun getCharacterComics(id: Int): OperationResult<BaseModelResponse> {
        val result = preferences?.get<BaseModelResponse>(id.toString())
        if(result != null){
            return OperationResult.Success(result)
        }
        return OperationResult.Error(Exception("Empty Data"))
    }

    override suspend fun saveCharacters(response: BaseModelResponse) {
        preferences?.put(response, Constants.CHARACTERS_KEY)
    }

    override suspend fun saveCharacterComics(id: String, response: BaseModelResponse) {
        preferences?.put(response, id)
    }

}