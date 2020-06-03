package com.varma.hemanshu.botsup.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * Util class for Shared Preferences
 */
class SharedPrefUtils private constructor() {

    //Setter for First launch from Shared preferences
    fun setIsFirstLaunch(boolean: Boolean) {
        editor?.putBoolean(PREF_IS_FIRST_LAUNCH, boolean)?.apply()
    }

    //Getter for First launch from Shared preferences
    fun isFirstLaunch() = sharedPreferences!!.getBoolean(PREF_IS_FIRST_LAUNCH, true)

    //Getter for New Notification ID.
    fun getNextNotificationId(): Int {
        var id = sharedPreferences!!.getInt(PREF_NOTIFICATION_ID, 0).plus(ADD_ONE)

        //Edge case for max value of Int, If so, then resetting
        if (id == Int.MAX_VALUE) id = 0
        editor?.putInt(PREF_NOTIFICATION_ID, id)?.apply()
        return id
    }

    //Getter for Current Notification ID.
    fun getCurrentNotificationId() = sharedPreferences!!.getInt(PREF_NOTIFICATION_ID, 0)

    companion object {
        private var sharedPref = SharedPrefUtils()
        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null

        const val PREF_NAME = "PREF_DB_STORE_AUTHENTICATION"
        const val PREF_NOTIFICATION_ID = "PREF_VAL_NOTIFICATION_ID"
        const val PREF_IS_FIRST_LAUNCH = "PREF_VAL_IS_FIRST_LAUNCH"
        const val ADD_ONE = 1

        @SuppressLint("CommitPrefEdits")
        @Synchronized
        fun getInstance(context: Context): SharedPrefUtils {
            if (sharedPreferences == null) {
                sharedPreferences =
                    context.getSharedPreferences(
                        PREF_NAME,
                        Context.MODE_PRIVATE
                    )
                editor = sharedPreferences?.edit()
            }
            return sharedPref
        }
    }
}