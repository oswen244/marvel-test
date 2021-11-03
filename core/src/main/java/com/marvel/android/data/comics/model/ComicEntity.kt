package com.marvel.android.data.comics.model

import android.os.Parcelable
import com.marvel.android.data.character.model.ThumbnailEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicEntity(val id: Int, val title: String, val thumbnail: ThumbnailEntity): Parcelable
