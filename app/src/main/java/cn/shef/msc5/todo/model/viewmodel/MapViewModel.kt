package cn.shef.msc5.todo.model.viewmodel

import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class MapViewModel : ViewModel() {

    val state: MutableState<MapState> = mutableStateOf(
        MapState(
            lastKnownLocation = null)
    )

    @SuppressLint("MissingPermission")
    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state.value = state.value.copy(
                        lastKnownLocation = task.result,
                    )
                }
            }
        } catch (e: SecurityException) {
            // Show error or something
        }
    }

    companion object {
        private val POLYGON_FILL_COLOR = Color.parseColor("#ABF44336")
    }
}

data class MapState(
    val lastKnownLocation: Location?
)
