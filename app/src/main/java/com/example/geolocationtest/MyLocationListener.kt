package com.example.geolocationtest

import android.location.Location
import android.location.LocationListener

class MyLocationListener : LocationListener {
    var lat = 0.0
    var lon = 0.0

    override fun onLocationChanged(location: Location) {
        lat = location.latitude
        lon = location.longitude
    }
}