package com.marvel.android.base

import java.math.BigInteger
import java.security.MessageDigest

class Utils {
    companion object{
        fun md5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        fun getTimeStamp(): Long{
            return System.currentTimeMillis()
        }
    }
}