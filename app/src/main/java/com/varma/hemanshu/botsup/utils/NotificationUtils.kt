package com.varma.hemanshu.botsup.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.varma.hemanshu.botsup.MainActivity
import com.varma.hemanshu.botsup.R
import timber.log.Timber

/**
 * Function to trigger Notification.
 * @param appContext Application Context
 * @param channelId Channel to send notification to
 * @param title Title in notification
 * @param message Message/Description of Notification
 * @param bitmapImage Image in Notification tray
 */
fun triggerNotification(
    appContext: Context,
    channelId: String,
    title: String,
    message: String,
    bitmapImage: Bitmap?
) {

    val notificationId = SharedPrefUtils.getInstance(appContext).getNextNotificationId()
    Timber.i("Notification ID: $notificationId")

    val notificationManager = ContextCompat.getSystemService(
        appContext,
        NotificationManager::class.java
    ) as NotificationManager

    // Activity/Fragment to open when pending intent is handled
    val contentIntent = Intent(appContext, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    //Pending Intent to handle click from notification panel
    val contentPendingIntent = PendingIntent.getActivity(
        appContext,
        notificationId,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    //Not showing image when no image is sent from fcm
    /*val largeIcon = image
        ?: BitmapFactory.decodeResource(
            appContext.resources,
            R.mipmap.ic_launcher_foreground
        )*/

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(bitmapImage)
        .bigLargeIcon(null)

    // Setting properties for notification
    val builder = NotificationCompat.Builder(appContext, channelId)
        .setSmallIcon(R.mipmap.ic_launcher_round)
        .setColor(appContext.resources.getColor(R.color.colorPrimary))
        .setContentTitle(title)
        .setContentText(message)
        .setAutoCancel(true)
        .setContentIntent(contentPendingIntent)

    //If no image is sent then, not setting the style
    if (bitmapImage != null) {
        builder.setStyle(bigPicStyle)
            .setLargeIcon(bitmapImage)
    }

    notificationManager.notify(notificationId, builder.build())
}

/**
 * Cancels all notifications.
 */
fun NotificationManager.cancelAllNotifications() {
    cancelAll()
}

/**
 * Cancels a specific notifications.
 * @param notificationId ID of the notification to cancel
 */
fun NotificationManager.cancelNotification(notificationId: Int) {
    cancel(notificationId)
}