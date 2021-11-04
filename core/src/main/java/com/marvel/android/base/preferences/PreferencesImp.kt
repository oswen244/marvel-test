package com.marvel.android.base.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

class PreferencesImp(val context: Context): PreferencesHelper {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("defPreferences", Context.MODE_PRIVATE)

    override fun save(keyName: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(keyName, text)
        editor.apply()
    }

    fun <T> put(`object`: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        save(key, jsonString)
    }

    override fun getValueString(keyName: String): String? {
        return sharedPref.getString(keyName, "")
    }

    inline fun <reified T> get(key: String): T? {
        val value = getValueString(key)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

}