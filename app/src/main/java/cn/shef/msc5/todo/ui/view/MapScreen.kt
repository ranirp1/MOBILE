package cn.shef.msc5.todo.ui.view

import android.content.Context
import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.TopBarType
import cn.shef.msc5.todo.model.viewmodel.MapState
import com.codingwithmitch.composegooglemaps.clusters.ZoneClusterManager
import cn.shef.msc5.todo.R

@Composable
fun MapScreen(
    state: MapState,
    setupClusterManager: (Context, GoogleMap) -> ZoneClusterManager,
    calculateZoneViewCenter: () -> LatLngBounds,
) {
    // Set properties using MapProperties which you can use to recompose the map
    val mapProperties = MapProperties(
        // Only enable if user has accepted location permissions.
        isMyLocationEnabled = state.lastKnownLocation != null,
    )
    val cameraPositionState = rememberCameraPositionState()
    val snackbarHostState = remember { SnackbarHostState() }
    BaseScaffold(
        showTopBar = true,
        topBarType = TopBarType.CENTER,
        showNavigationIcon = true,
        title = stringResource(R.string.todo_map),
        hostState = snackbarHostState) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            cameraPositionState = cameraPositionState
        ) {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            MapEffect(state.clusterItems) { map ->
                if (state.clusterItems.isNotEmpty()) {
                    val clusterManager = setupClusterManager(context, map)
                    map.setOnCameraIdleListener(clusterManager)
                    map.setOnMarkerClickListener(clusterManager)
                    state.clusterItems.forEach { clusterItem ->
                        map.addPolygon(clusterItem.polygonOptions)
                    }
                    map.setOnMapLoadedCallback {
                        if (state.clusterItems.isNotEmpty()) {
                            scope.launch {
                                cameraPositionState.animate(
                                    update = CameraUpdateFactory.newLatLngBounds(
                                        calculateZoneViewCenter(),
                                        0
                                    ),
                                )
                            }
                        }
                    }
                }
            }

            // https://github.com/googlemaps/android-maps-compose#obtaining-access-to-the-raw-googlemap-experimental
            MarkerInfoWindow(
                state = rememberMarkerState(position = LatLng(49.1, -122.5)),
                snippet = "Some stuff",
                onClick = {
                    // This won't work :(
                    System.out.println("Mitchs_: Cannot be clicked")
                    true
                },
                draggable = true
            )
        }
    }

}

private suspend fun CameraPositionState.centerOnLocation(
    location: Location
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        LatLng(location.latitude, location.longitude),
        15f
    ),
)