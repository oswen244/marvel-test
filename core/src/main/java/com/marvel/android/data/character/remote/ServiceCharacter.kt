package com.marvel.android.data.character.remote

import com.marvel.android.data.character.model.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceCharacter {
    @GET("character/?page=1")
    suspend fun getCharacters(): Response<CharacterListResponse>
}