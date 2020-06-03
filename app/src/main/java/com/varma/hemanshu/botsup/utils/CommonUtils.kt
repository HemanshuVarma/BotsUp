package com.varma.hemanshu.botsup.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.content.ContextCompat

/**
 * Utils class for common methods
 */
class CommonUtils {

    companion object {

        /**
         * Creates channel for Android version Oreo, API 26 and above
         * @param context App context
         * @param channelId Unique ID of Channel
         * @param channelName Name of Channel to be created for user
         * @param channelDescription Description of channel for user
         * @param notificationImportance Setting importance of notification
         * Note: Any customisation here will require clean install of app to reflect changes
         */
        fun createChannel(
            context: Context,
            channelId: String,
            channelName: String,
            channelDescription: String,
            notificationImportance: Int
        ) {
            val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager

            //Channel is required for Android Oreo and above
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create channel to show notifications
                val notificationChannel = NotificationChannel(
                    channelId,
                    channelName,
                    notificationImportance
                )
                    .apply {
                        enableLights(true)
                        lightColor = Color.CYAN
                        enableVibration(true)
                        description = channelDescription
                        setShowBadge(true)
                    }

                notificationManager.createNotificationChannel(notificationChannel)
            }
        }
    }
}