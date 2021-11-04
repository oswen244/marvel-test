package com.marvel.android.base.preferences

interface PreferencesHelper {
    fun save(keyName: String, text: String)
    fun getValueString(keyName: String): String?
}