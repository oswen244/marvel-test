package com.marvel.android.domain

import com.marvel.android.BuildConfig
import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.base.Utils
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.data.character.repository.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    suspend fun execute(limit: Int?, offset: Int?): OperationResult<BaseModelResponse> {
        val ts = Utils.getTimeStamp()
        val md5 = Utils.md5("$ts${BuildConfig.privateKey}${BuildConfig.publicKey}")
        return charactersRepository.getCharacters(limit, offset, ts, md5)
    }
}