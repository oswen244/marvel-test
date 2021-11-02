package com.marvel.android.data.comics.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicItems(val resourceURI: String, val name: String): Parcelable
