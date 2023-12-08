package cn.shef.msc5.todo.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import cn.shef.msc5.todo.base.BaseActivity
import cn.shef.msc5.todo.model.viewmodel.MapViewModel
import cn.shef.msc5.todo.ui.theme.AppTheme
import cn.shef.msc5.todo.ui.view.LocationScreen
import cn.shef.msc5.todo.ui.view.MapScreen

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 25/11/2023 05:37
 */
class MapsActivity : BaseActivity(), OnMapReadyCallback {

    private var permissionsRequired = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)

    private val PERMISSON_REQUESTCODE = 110
    private val LOCATION_CODE = 1315
    private var isNeedCheck = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if(isNeedCheck) checkPermission(permissionsRequired)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapScreen(
                        state = viewModel.state.value,
                        intent = this.intent
                    )
                }
            }
        }
    }

    private fun checkPermission(permissions: Array<String>) {
        Log.d("MapsActivity", "checkPermission")
        val needRequestPermissionList = findDeniedPermissions(permissions)
        if (null != needRequestPermissionList &&
            needRequestPermissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(this,
                needRequestPermissionList.toTypedArray(),
                PERMISSON_REQUESTCODE)
        }
    }

    private fun findDeniedPermissions(permissions: Array<String>): List<String> {
        var needRequestPermissionList = ArrayList<String>()
        for (perm in permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissionList.add(perm)
            }else{
                viewModel.getDeviceLocation(fusedLocationProviderClient)
            }
        }
        return needRequestPermissionList
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, paramArrayOfInt: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, paramArrayOfInt)
        when (requestCode) {
            PERMISSON_REQUESTCODE -> if (!verifyPermissions(paramArrayOfInt)) {
                isNeedCheck = false
            }
            LOCATION_CODE -> {
                if (paramArrayOfInt.isNotEmpty() &&
                    paramArrayOfInt[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("MapsActivity", "onRequestPermissionsResult: yes")
                } else {
                    Toast.makeText(this@MapsActivity,
                        "Positioning permission is prohibited " +
                                "and related map functions cannot be used!", Toast.LENGTH_LONG).show()
                }

            }
        }

    }

    private fun verifyPermissions(grantResults: IntArray): Boolean {
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val viewModel: MapViewModel by viewModels()

    override fun onMapReady(googleMap: GoogleMap) {

    }
}

