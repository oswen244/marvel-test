package com.marvel.android.data.comics.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicEntity(val collectionURI: String, val items: List<ComicItems>): Parcelable
