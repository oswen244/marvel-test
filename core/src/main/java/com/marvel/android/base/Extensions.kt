package com.marvel.android.base

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Extensions {
    internal inline fun <reified T> Gson.fromJsonString(json: String) =
        fromJson<T?>(json, object : TypeToken<T?>() {}.type)
}