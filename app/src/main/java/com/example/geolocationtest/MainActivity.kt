package com.example.geolocationtest

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.search_location_button)

        btn.setOnClickListener { getLocation() }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1
            )
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_NETWORK_STATE), 1
            )
            return
        }

        val myLocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val myLocationListener = MyLocationListener()

        myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, myLocationListener)

        if (!myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show()
        }
        initMaps(MyLocationListener.latitude, MyLocationListener.longitude)
    }

    private fun initMaps(latitude: Double, longitude: Double) {
        val webView = findViewById<WebView>(R.id.web_view)
        // to enable JS on chrome
        webView.getSettings().setJavaScriptEnabled(true)
        webView.loadUrl("https://www.google.com/maps/search/?api=1&query=$latitude,$longitude")

        val yourLocationText = findViewById<TextView>(R.id.your_geolocation)
        yourLocationText.text = "SUA GEOLOCALIZAÇÃO: \nLatitude: ${MyLocationListener.latitude} \nLongitude: ${MyLocationListener.longitude}"
    }
}