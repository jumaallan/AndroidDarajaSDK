package com.androidstudy.mpesa.utils

import android.content.Context
import android.content.Context.*
import com.androidstudy.mpesa.BuildConfig
import java.util.*

object AppUtils {
    fun UUID(): String {
        return UUID.randomUUID().toString()
    }

    val passKey: String
        get() = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"

    fun saveAccessToken(context: Context, accessToken: String) {
        val mSettings = context.getSharedPreferences(BuildConfig.APPLICATION_ID , MODE_PRIVATE)
        val editor = mSettings.edit()
        val cal = Calendar.getInstance()
        cal.add(Calendar.HOUR, 1)
        val oneHourAfter = cal.time
        editor.putString("accessToken", accessToken)
        editor.putString("expiry_date", oneHourAfter.toString())
        editor.apply()
    }

    fun getAccessToken(context: Context): String? {
        val mSettings = context.getSharedPreferences(BuildConfig.APPLICATION_ID , MODE_PRIVATE)
        return mSettings.getString("accessToken", "")
    }

    //TODO(check for if access token expired)
    fun expired(context: Context): Boolean {
        val mSettings = context.getSharedPreferences(BuildConfig.APPLICATION_ID , MODE_PRIVATE)
        return true
    }
}