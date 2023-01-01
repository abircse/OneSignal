package com.coxtunes.onesignal.handler

import android.util.Log
import com.onesignal.OSNotificationOpenResult
import com.onesignal.OneSignal


class MyNotificationOpenedHandler : OneSignal.NotificationOpenedHandler {
    // This fires when a notification is opened by tapping on it.
    fun notificationOpened(result: OSNotificationOpenResult) {
        val actionType: ActionType = result.action.type
        val data: JSONObject = result.notification.payload.additionalData
        val activityToBeOpened: String

        //While sending a Push notification from OneSignal dashboard
        // you can send an addtional data named "activityToBeOpened" and retrieve the value of it and do necessary operation
        //If key is "activityToBeOpened" and value is "AnotherActivity", then when a user clicks
        //on the notification, AnotherActivity will be opened.
        //Else, if we have not set any additional data MainActivity is opened.
        if (data != null) {
            activityToBeOpened = data.optString("activityToBeOpened", null)
            if (activityToBeOpened != null && activityToBeOpened == "AnotherActivity") {
                Log.i("OneSignalExample", "customkey set with value: $activityToBeOpened")
                val intent = Intent(MyApplication.getContext(), AnotherActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK)
                MyApplication.getContext().startActivity(intent)
            } else if (activityToBeOpened != null && activityToBeOpened == "MainActivity") {
                Log.i("OneSignalExample", "customkey set with value: $activityToBeOpened")
                val intent = Intent(MyApplication.getContext(), MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK)
                MyApplication.getContext().startActivity(intent)
            } else {
                val intent = Intent(MyApplication.getContext(), MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK)
                MyApplication.getContext().startActivity(intent)
            }
        }

        //If we send notification with action buttons we need to specidy the button id's and retrieve it to
        //do the necessary operation.
        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID)
            if (result.action.actionID.equals("ActionOne")) {
                Toast.makeText(
                    MyApplication.getContext(),
                    "ActionOne Button was pressed",
                    Toast.LENGTH_LONG
                ).show()
            } else if (result.action.actionID.equals("ActionTwo")) {
                Toast.makeText(
                    MyApplication.getContext(),
                    "ActionTwo Button was pressed",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}