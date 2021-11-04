package com.marvel.android.domain

import com.marvel.android.BuildConfig
import com.marvel.android.base.BaseModelResponse
import com.marvel.android.base.OperationResult
import com.marvel.android.base.Utils
import com.marvel.android.data.character.repository.CharactersRepository

class GetCharacterComicsUseCase(private val charactersRepository: CharactersRepository) {
    suspend fun execute(id: Int, limit: Int?, offset: Int?, isNetWorkAvailable: Boolean): OperationResult<BaseModelResponse>{
        val ts = Utils.getTimeStamp()
        val md5 = Utils.md5("$ts${BuildConfig.privateKey}${BuildConfig.publicKey}")
        return charactersRepository.getCharacterComics(id, Utils.getLimit(limit), offset, ts, md5, isNetWorkAvailable)
    }
}