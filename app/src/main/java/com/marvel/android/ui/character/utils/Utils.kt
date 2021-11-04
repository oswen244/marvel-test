package com.marvel.android.ui.character.utils

import android.content.Context
import android.net.ConnectivityManager

class Utils {
    companion object{
        fun getLimit(inputLimit: Int): Int{
            if(inputLimit > 100){
                return 100
            }else if (inputLimit == 0){
                return 1
            }
            return inputLimit
        }

        fun isConnectedToInternet(context: Context): Boolean{
            var status = false
            val cm = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetwork != null && cm.getNetworkCapabilities(cm.activeNetwork) != null) {
                status = true
            }
            return status
        }
    }
}