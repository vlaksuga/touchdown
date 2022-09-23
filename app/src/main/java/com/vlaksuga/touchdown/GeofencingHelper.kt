package com.vlaksuga.touchdown

import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest

class GeofencingHelper(base: Context?) : ContextWrapper(base) {
    companion object {
        const val TAG = "Geofencing Helper"
        const val LAT = 37.5206326
        const val REGION_RADIUS = 100f
    }

    lateinit var pendingIntent : PendingIntent

    fun geofencingRequest(geofence: Geofence) : GeofencingRequest {
        return GeofencingRequest.Builder()
            .addGeofence(geofence)
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .build()
    }

    

}