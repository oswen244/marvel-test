package com.marvel.android.data.comics.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicURIEntity(val collectionURI: String, val available: Int): Parcelable
