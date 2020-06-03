package com.varma.hemanshu.botsup.messaging

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.varma.hemanshu.botsup.R
import com.varma.hemanshu.botsup.utils.FirebaseUtils
import com.varma.hemanshu.botsup.utils.triggerNotification
import timber.log.Timber

class MessagingService : FirebaseMessagingService() {

    /**
     * Called if InstanceID token is updated.
     * This may occur if the security of the previous token had been compromised.
     * @param token Retrieved from Firebase
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.i("New Token received $token")

        //Sending the generated token to FireStore
        FirebaseUtils.sendTokenToServer(token)
    }

    /**
     * Called when message is received.
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     * If app is in Foreground, Notification and payload is received here
     * If app is in Background, Notification in system tray and payload is received here in the "intent"
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        /*
        //Parsed by Postman
        remoteMessage.let { message ->

            val title = message.data["title"]
            val description = message.data["body"]

            Log.i(TAG, "Notification data payload: $title")
            Log.i(TAG, "Notification data payload: $description")
            sendNotification(title!!, description!!)
        }
        */

        //App running in Foreground. Check if message contains notification payload.
        remoteMessage.notification?.let {
            val title = it.title
            val message = it.body
            val bitmapImage = FirebaseUtils.getBitmapFromUrl(it.imageUrl.toString())

            triggerNotification(
                applicationContext, getString(R.string.fcm_notification_channel_id),
                title!!, message!!, bitmapImage
            )

            Timber.i("Notification title: $title")
            Timber.i("Notification message: $message")
        }

        //App running in Background. Check if message contains data payload.
        remoteMessage.data.isNotEmpty().let {
            Timber.i("Message data payload: ${remoteMessage.data}")
        }
    }
}