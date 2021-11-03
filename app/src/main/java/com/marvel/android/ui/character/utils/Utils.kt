package com.marvel.android.ui.character.utils

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
    }
}