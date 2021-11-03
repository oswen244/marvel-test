package com.marvel.android.data.character.model

import android.os.Parcelable
import com.marvel.android.data.comics.model.ComicURIEntity
import kotlinx.parcelize.Parcelize


@Parcelize
data class CharacterEntity(val id: Int,
                           val name: String?,
                           val description: String,
                           val thumbnail: ThumbnailEntity?,
                           val comics: ComicURIEntity?): Parcelable