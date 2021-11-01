package com.marvel.android.data.character.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CharacterEntity(val id: Int,
                           val name: String?,
                           val status: String,
                           val species: String?,
                           val image: String?): Parcelable