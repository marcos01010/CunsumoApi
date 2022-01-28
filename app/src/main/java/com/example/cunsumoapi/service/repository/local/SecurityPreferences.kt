package com.example.cunsumoapi.service.repository.local

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {
    private val mPreferences: SharedPreferences = context.getSharedPreferences("taskShared",Context.MODE_PRIVATE)

    fun store(key: String, value: String){
        mPreferences.edit().putString(key, value).apply()
    }
}