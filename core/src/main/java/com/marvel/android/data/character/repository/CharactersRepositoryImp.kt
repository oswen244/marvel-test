package com.marvel.android.data.character.repository

import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.data.character.remote.RemoteDataSource

class CharactersRepositoryImp(private val remoteDataSource: RemoteDataSource):
    CharactersRepository {
    override suspend fun getCharacters(): OperationResult<List<CharacterEntity>> {
        return remoteDataSource.getCharacters()
    }

}