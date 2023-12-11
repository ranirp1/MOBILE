package cn.shef.msc5.todo.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import cn.shef.msc5.todo.utilities.ImageUtil


class CaptureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO will also need pickedImageUri for storing in DB ...
            val imageUtil = ImageUtil()
            var cameraImageUri by remember { mutableStateOf<Uri?>(null) }
            var cameraLaunched by remember { mutableStateOf(false) }
            val imageFromCameraLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.TakePicture()
            ) { captured ->
                if (!captured) {
                    cameraImageUri = null
                } else {
                    val resultIntent = Intent().apply {
                        putExtra("capturedImageUri", cameraImageUri.toString())
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                }
                finish()
            }

            var hasCameraPermission by remember { mutableStateOf(false) }
            if (checkCameraPermission() && checkExternalStoragePermission()) {
                hasCameraPermission = true
            } else {
                val requestPermissionLauncher =
                    rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestPermission()
                    ) { isGranted: Boolean ->
                        if (isGranted) {
                            hasCameraPermission = true
                        } else {
                            Toast.makeText(
                                getBaseContext(),
                                "Camera Permission denied", Toast.LENGTH_SHORT
                            ).show()
                            // Below is likely redundant
                            hasCameraPermission = false
                            finish()
                        }
                    }
                SideEffect {
                    requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            }

            if(hasCameraPermission && !cameraLaunched){
                val authority = "$packageName.provider"
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    cameraImageUri =
                        FileProvider.getUriForFile(
                            applicationContext,
                            authority,
                            imageUtil.newImageFile(applicationContext)
                        )
                } else {
                    cameraImageUri = Uri.fromFile(imageUtil.newImageFile(applicationContext))
                }

                cameraImageUri = FileProvider.getUriForFile(
                    applicationContext,
                    authority,
                    imageUtil.newImageFile(applicationContext)
                )

                imageFromCameraLauncher.launch(cameraImageUri)
                cameraLaunched = true
            }
        }
    }

    private fun checkCameraPermission(): Boolean {
        return PackageManager.PERMISSION_GRANTED ==
                ActivityCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.CAMERA
                )
    }

    private fun checkExternalStoragePermission(): Boolean {
        return PackageManager.PERMISSION_GRANTED ==
                ActivityCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
    }
}