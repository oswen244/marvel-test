package com.marvel.android.base

data class BaseDataModel(val offset: Int, val limit: Int, val total: Int, val count: Int, val results: List<Any>)
