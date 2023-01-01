package com.coxtunes.onesignal

import android.app.Application
import com.onesignal.OneSignal

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configOneSignal()
    }

    private fun configOneSignal() {

        /** Logging set to help debug issues, remove before releasing your app **/
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        /** OneSignal Initialization **/
        OneSignal.initWithContext(this)
        OneSignal.setAppId("Place_Your_One_Signal_ID_There")

        /** For Android 33 User Notification Permission **/
        OneSignal.promptForPushNotifications()

        /** This function will use when notification will be disabled **/
        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)

        /** This function will use when user click on Notification for open**/
        OneSignal.setNotificationOpenedHandler {}

        /** This function will use for Received Notification in background **/
        OneSignal.setNotificationWillShowInForegroundHandler { event->

            /** ========= YOU CAN STORE NOTIFICATION FROM HERE using following date from even ======== **/
            /**
            title = event!!.notification.title,
            message = event.notification.body,
            createdAt = event.notification.sentTime.toTimeDateString()
            **/
        }
    }
}