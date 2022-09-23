package com.vlaksuga.touchdown

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val TAG = "XX : GeofenceBroadcastReceiver"
    }

    lateinit var geofencingEvent: GeofencingEvent
    lateinit var notificationHelper: NotificationHelper

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { con ->
            notificationHelper = NotificationHelper(con)
            Toast.makeText(context, "Received", Toast.LENGTH_SHORT).show()
            intent?.let { int ->
                geofencingEvent = GeofencingEvent.fromIntent(int)!!
                if(geofencingEvent.hasError()) {
                    Log.d(TAG, "onReceive : has Error")
                }
                geofencingEvent.triggeringGeofences?.let { list ->
                    for(geofence in list) {
                        Log.d(TAG, "onReceive : " + geofence.requestId)
                    }
                }
                when(geofencingEvent.geofenceTransition) {
                    Geofence.GEOFENCE_TRANSITION_ENTER -> {
                        Toast.makeText(context, "GEOFENCE_ENTER", Toast.LENGTH_SHORT).show()
                        notificationHelper.sendHighPriorityNotification("Enter", "", LandingActivity::class.java)
                    }
                    Geofence.GEOFENCE_TRANSITION_DWELL -> {
                        Toast.makeText(context, "GEOFENCE_DWELL", Toast.LENGTH_SHORT).show()
                        notificationHelper.sendHighPriorityNotification("DWELL", "", LandingActivity::class.java)
                    }
                    Geofence.GEOFENCE_TRANSITION_EXIT -> {
                        Toast.makeText(context, "GEOFENCE_EXIT", Toast.LENGTH_SHORT).show()
                        notificationHelper.sendHighPriorityNotification("EXIT", "", LandingActivity::class.java)
                    }
                    else -> {}
                }
            }
        }
    }
}