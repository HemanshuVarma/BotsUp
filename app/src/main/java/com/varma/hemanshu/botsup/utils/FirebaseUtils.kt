package com.varma.hemanshu.botsup.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import com.google.firebase.Timestamp
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

/**
 * Util class for Firebase Functions
 */
class FirebaseUtils {
    companion object {

        //Storing token in single project of firebase. So naming AppName with bucket name.
        //Feel free to use your own naming convention.
        private const val COLLECTION_NAME = "BotsUp-FCM-Token"

        //Instance of Firebase FireStore
        private val dbRef by lazy { Firebase.firestore.collection(COLLECTION_NAME) }

        /**
         * A method to subscribe to topic for FCM
         * @param topic Name fo topic to subscribe on
         */
        fun subscribeTopic(topic: String) {
            FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener { task ->
                    var message = "Success subscribing to $topic"
                    if (!task.isSuccessful) {
                        message = "Failed subscribing to $topic"
                    }
                    Timber.i(message)
                }
        }

        /**
         *To get a Bitmap image from the URL received
         */
        fun getBitmapFromUrl(imageUrl: String?): Bitmap? {
            return try {
                Timber.i("Image to download: $imageUrl")
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        /**
         * Sends freshly generated token to FireStore
         * @param appToken Token to send
         */
        fun sendTokenToServer(appToken: String) {
            val deviceName = Build.MANUFACTURER + " " + Build.MODEL
            val timeStamp = Timestamp(Date())

            Timber.i("Device is $deviceName")
            Timber.i("Timestamp is $timeStamp")

            val data = hashMapOf(
                "timestamp" to timeStamp,
                "device" to deviceName,
                "token" to appToken
            )

            // Add a new document to collection FCM-Token
            dbRef.document(deviceName)
                //SetOptions.merge() is used inorder to have unique entries in Db.
                //If the device name is already present then it will be overwritten,
                //otherwise new entry will be created
                .set(data, SetOptions.merge())
                .addOnSuccessListener {
                    Timber.i("Document added successfully")
                }
                .addOnFailureListener { error ->
                    Timber.w("Error adding document $error")
                }
        }
    }
}