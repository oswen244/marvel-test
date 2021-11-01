package com.marvel.android.data.character.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CharacterListResponse(val results: List<CharacterEntity>): Parcelable