package com.coxtunes.onesignal.handler

import android.support.v4.app.NotificationCompat
import android.util.Log
import java.math.BigInteger

class MyNotificationExtenderService : NotificationExtenderService() {
    protected fun onNotificationProcessing(receivedResult: OSNotificationReceivedResult?): Boolean {
        val overrideSettings = OverrideSettings()
        overrideSettings.extender = object : Extender() {
            fun extend(builder: NotificationCompat.Builder): NotificationCompat.Builder {
                // Sets the background notification color to Red on Android 5.0+ devices.
                val icon: Bitmap = BitmapFactory.decodeResource(
                    MyApplication.getContext().getResources(),
                    R.drawable.ic_stat_onesignal_default
                )
                builder.setLargeIcon(icon)
                return builder.setColor(BigInteger("FF0000FF", 16).toInt())
            }
        }
        val displayedResult: OSNotificationDisplayedResult = displayNotification(overrideSettings)
        Log.d(
            "OneSignalExample",
            "Notification displayed with id: " + displayedResult.androidNotificationId
        )
        return true
    }
}