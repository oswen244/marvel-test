package com.marvel.android.data.character.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThumbnailEntity(val path: String, val extension: String): Parcelable
