package com.varma.hemanshu.botsup.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.varma.hemanshu.botsup.Constants

/**
 * Util class for Shared Preferences
 */
class SharedPrefUtils private constructor() {

    //Getter for New Notification ID.
    fun getNextNotificationId(): Int {
        var id =
            sharedPreferences!!.getInt(Constants.PREF_NOTIFICATION_ID, 0).plus(Constants.ADD_ONE)

        //Edge case for max value of Int, If so, then resetting
        if (id == Int.MAX_VALUE) id = 0
        editor?.putInt(Constants.PREF_NOTIFICATION_ID, id)?.apply()
        return id
    }

    //Getter for Current Notification ID.
    fun getCurrentNotificationId() = sharedPreferences!!.getInt(Constants.PREF_NOTIFICATION_ID, 0)

    fun getBooleanValue(key: String) = sharedPreferences!!.getBoolean(key, false)

    fun setBooleanValue(key: String, value: Boolean) = editor?.putBoolean(key, value)?.apply()

    fun getStringValue(key: String) = sharedPreferences!!.getString(key, null)

    fun setStringValue(key: String, value: String) = editor?.putString(key, value)?.apply()

    companion object {
        private var sharedPref = SharedPrefUtils()
        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null

        @SuppressLint("CommitPrefEdits")
        @Synchronized
        fun getInstance(context: Context): SharedPrefUtils {
            if (sharedPreferences == null) {
                sharedPreferences =
                    context.getSharedPreferences(
                        Constants.PREF_NAME,
                        Context.MODE_PRIVATE
                    )
                editor = sharedPreferences?.edit()
            }
            return sharedPref
        }
    }
}