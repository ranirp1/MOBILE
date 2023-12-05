package cn.shef.msc5.todo.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.shef.msc5.todo.model.viewmodel.LocationViewModel
import cn.shef.msc5.todo.services.GeoLocationService


class GeoLocationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasPermission()) {
            requestFineLocationPermission()
        }
        setContent {
            val locationViewModel = viewModel<LocationViewModel>()
            GeoLocationService.locationViewModel = locationViewModel
            MaterialTheme {
                Surface( modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (locationViewModel.valid()) {
                        GeoLocation( ::openMaps,
                            locationViewModel.latitude.toString(),
                            locationViewModel.longitude.toString()
                        )
                    } else {
                        Text("LOCATION UNKNOWN")
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        @SuppressLint("MissingPermission")
        if (hasPermission()) {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                GeoLocationService.updateLatestLocation(location)
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 0.0f, GeoLocationService
            )
        }
    }

    override fun onPause() {
        super.onPause()
        val locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.removeUpdates(GeoLocationService)
    }

    fun openMaps(lat: String, lon: String) {
        val intentUri = Uri.parse("geo:$lat,$lon")
        val intent = Intent(Intent.ACTION_VIEW, intentUri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

    private val GPS_LOCATION_PERMISSION_REQUEST = 1
    private fun requestFineLocationPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf( android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION ),
            GPS_LOCATION_PERMISSION_REQUEST
        )
    }

    private fun hasPermission(): Boolean {
        return PackageManager.PERMISSION_GRANTED ==
                ActivityCompat.checkSelfPermission(
                    applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION )
    }
}

@Composable
fun GeoLocation(openMaps: (String, String) -> Unit, lat: String, lon: String ) {
    Column {
        Text("Latitude: $lat")
        Text("Longitude: $lon")
        Button(onClick = {
            openMaps(lat, lon)
        }) { Text("Open Map") }
    }
}
