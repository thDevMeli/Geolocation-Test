package com.example.geolocationtest

import android.location.Location
import android.location.LocationListener

class MyLocationListener : LocationListener {
    override fun onLocationChanged(location: Location) {
        latitude = location.latitude
        longitude = location.longitude
    }

    companion object {
        var latitude = -23.52409
        var longitude = -46.76446
    }
}