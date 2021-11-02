package com.marvel.android.data.character.remote

import com.marvel.android.base.BaseModelResponse
import com.marvel.android.data.character.model.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceCharacter {
    @GET("characters")
    suspend fun getCharacters(@Query("apikey") apiKey: String,
                              @Query("ts") ts: Long,
                              @Query("hash") hash: String,
                              @Query("limit") limit: Int?,
                              @Query("offset") offset: Int?): Response<BaseModelResponse>
}