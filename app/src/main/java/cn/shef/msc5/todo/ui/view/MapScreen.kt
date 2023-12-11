package cn.shef.msc5.todo.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.TopBarType
import cn.shef.msc5.todo.model.viewmodel.MapState
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.MapsActivity
import cn.shef.msc5.todo.utilities.MapItems
import cn.shef.msc5.todo.utilities.SubTaskConverter
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    state: MapState, intent: Intent
) {
    val TAG = "MapScreen"
    val context = LocalContext.current as MapsActivity

    // Set properties using MapProperties which you can use to recompose the map
    val mapProperties = MapProperties(
        // Only enable if user has accepted location permissions.
        isMyLocationEnabled = state.lastKnownLocation != null,
    )
    val cameraPositionState = rememberCameraPositionState()
    val snackbarHostState = remember { SnackbarHostState() }
    val startActivity = remember { intent.getIntExtra("startActivity", 0) }

    var taskList: List<MapItems>? = null
    var title1 = stringResource(R.string.todo_task_map)
    Log.d("MapScreen startActivity", intent.getIntExtra("startActivity", 0).toString())

    if (startActivity == 0) {
        title1 = stringResource(R.string.todo_task_map)
        taskList = intent.getStringExtra("taskList")?.let { SubTaskConverter.toTaskList(it) }
    } else if (startActivity == 1) {
        title1 = stringResource(R.string.todo_map)
    }

    BaseScaffold(
        showTopBar = true,
        topBarType = TopBarType.CENTER,
        showNavigationIcon = true,
        title = title1,
        hostState = snackbarHostState
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            GoogleMap(modifier = Modifier.fillMaxSize(),
                properties = mapProperties,
                cameraPositionState = cameraPositionState,
                onMapClick = {
                    Log.d(
                        "MapScreen",
                        "it.latitude + " + it.latitude + "it.longitude + " + it.longitude
                    )
                    if (startActivity == 1) {
                        val intent = Intent()
                        intent.putExtra("latitude", it.latitude)
                        intent.putExtra("longitude", it.longitude)
                        context.setResult(Activity.RESULT_OK, intent)
                        context.finish();
                    }

                }) {
                if (startActivity == 0) {
                    val markerClick: (Marker) -> Boolean = {
                        Log.d(TAG, "${it.title} was clicked")
                        cameraPositionState.projection?.let { projection ->
                            Log.d(TAG, "The current projection is: $projection")
                        }
                        false
                    }
                    if (taskList != null) {
                        Log.d("MapScreen", "taskListState size: " + taskList.size)
                        for (i in taskList.indices) {
                            val task = taskList[i]
                            val taskState = rememberMarkerState(
                                position = LatLng(
                                    task.latitude, task.longitude
                                )
                            )
                            var circleCenter by remember {
                                mutableStateOf(
                                    LatLng(
                                        task.latitude, task.longitude
                                    )
                                )
                            }
                            if (taskState.dragState == DragState.END) {
                                circleCenter = taskState.position
                            }
                            MarkerInfoWindowContent(
                                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE),
                                title = task.title,
                                state = taskState,
                                onClick = markerClick,
                                draggable = false,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(88.dp)
                                        .height(36.dp)
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(it.title ?: "Title", color = Color.Black)
                                }

                            }
                            Circle(
                                center = circleCenter,
                                fillColor = MaterialTheme.colors.secondary,
                                strokeColor = MaterialTheme.colors.secondaryVariant,
                                radius = 50.0,
                            )
                        }
                    }
                }
            }
        }
        // Center camera to include all the Zones.
        LaunchedEffect(state.lastKnownLocation) {
            if (state.lastKnownLocation != null) {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLng(
                        LatLng(
                            state.lastKnownLocation.latitude, state.lastKnownLocation.latitude
                        )
                    )
                )
            } else {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLng(LatLng(53.38110821, -1.47992193))
                )
            }
        }
    }
}