package com.ttd.dasasahitya.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.ttd.dasasahitya.DasaSahityaApp

object EncryptedSharedPreferenceUtil {
    val isEnglish :Lazy<String> = lazy { "isEnglish" }
    private fun getMasterKey(context:Context):MasterKey{
        return MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    }
    private fun getSharedPreference(context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            DasaSahityaApp.applicationContext.packageName,
            getMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveSP(key: String, value: String, context: Context) {
        getSharedPreference(context).edit().putString(key, value).apply()
    }

    fun getSP(key: String, context: Context): String? {
        return getSharedPreference(context).getString(key, null)
    }

    fun saveSPBoolean(key: String, value: Boolean, context: Context) {
        getSharedPreference(context).edit().putBoolean(key, value).apply()
    }

    fun getSPBoolean(key: String, context: Context): Boolean {
        return getSharedPreference(context).getBoolean(key, false)
    }

    fun clearSharedPreferences(context: Context) {
        val editor: SharedPreferences.Editor = getSharedPreference(context).edit()
        editor.clear()
        editor.apply()
    }
}