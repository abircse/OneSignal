package com.coxtunes.onesignal.handler

import android.util.Log
import com.onesignal.OSNotification
import com.onesignal.OneSignal
import org.json.JSONObject


//This will be called when a notification is received while your app is running.
class MyNotificationReceivedHandler : OneSignal.NotificationReceivedHandler {
    fun notificationReceived(notification: OSNotification) {
        val data: JSONObject = notification.payload.additionalData
        val customKey: String?
        if (data != null) {
            //While sending a Push notification from OneSignal dashboard
            // you can send an addtional data named "customkey" and retrieve the value of it and do necessary operation
            customKey = data.optString("customkey", null)
            if (customKey != null) Log.i("OneSignalExample", "customkey set with value: $customKey")
        }
    }
}