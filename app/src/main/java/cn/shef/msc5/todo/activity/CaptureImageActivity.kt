package cn.shef.msc5.todo.activity

import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import java.io.File


class CaptureImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO will also need pickedImageUri for storing in DB ...
            var pickedImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
            val imageFromGalleryLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.PickVisualMedia() ) {
                    uri: Uri? ->
                if (uri == null) {
                    pickedImageBitmap = null
                } else {
                    pickedImageBitmap = ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(getContentResolver(),
                            uri)).asImageBitmap()
                }
            }
            var cameraImageUri by remember { mutableStateOf<Uri?>(null) }
            var cameraImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
            val imageFromCameraLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.TakePicture() ) { captured ->
                if (!captured) {
                    cameraImageBitmap = null
                    cameraImageUri = null
                } else {
                    cameraImageBitmap = getImageBitmap(cameraImageUri)
                }
                Log.i("picture_eg", "$captured $cameraImageUri")
            }

            var hasCameraPermission by remember { mutableStateOf(false) }
            if (checkCameraPermission() && checkCameraPermission2()) {
                hasCameraPermission = true
            } else {
                val requestPermissionLauncher =
                    rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestPermission() ) {
                            isGranted: Boolean ->
                        if (isGranted) {
                            hasCameraPermission = true
                        } else {
                            Toast.makeText(getBaseContext(),
                                "Camera Permission denied", Toast.LENGTH_SHORT).show()
                            // Below is likely redundant
                            hasCameraPermission = false
                        }
                    }
                SideEffect {
                    requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            }
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        pickedImageBitmap?.let { imageBitmap ->
                            Image(imageBitmap, null)
                        }
                        Button(onClick = {
                            imageFromGalleryLauncher.launch(
                                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                        ) {
                            Text("Open Gallery")
                        }
                        if (hasCameraPermission) {
                            cameraImageBitmap?.let { cameraBitmap ->
                                Image(cameraBitmap, null)
                            }
                            Button(onClick = {
                                cameraImageBitmap = null
                                val authority = "$packageName.provider"
                                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                                cameraImageUri = FileProvider.getUriForFile(
                                    applicationContext,
                                    authority,
                                    newImageFile()
                                )}else{
                                    cameraImageUri = Uri.fromFile(newImageFile())
                                }
                                cameraImageUri = FileProvider.getUriForFile(
                                    applicationContext,
                                    authority,
                                    newImageFile()
                                )
                                imageFromCameraLauncher.launch(cameraImageUri)
                            }) {
                                Text("Open Camera")
                            }
                        } else {
                            Text("Camera Permission needs to be granted")
                        }
                    }
                }
                }
            }
        }

    private fun newImageFile(): File{
        val timeMillis = System.currentTimeMillis().toString()
        val storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("SNAP-$timeMillis", ".jpg",storageDir)
    }

    private fun getImageBitmap(uri: Uri?): ImageBitmap? {
        var result: ImageBitmap? = null
        if (uri != null) {
            result = ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(getContentResolver(),
                    uri)).asImageBitmap()
        }
        return result
    }

    private fun checkCameraPermission(): Boolean {
        return PackageManager.PERMISSION_GRANTED ==
                ActivityCompat.checkSelfPermission(applicationContext,
                    android.Manifest.permission.CAMERA
                )
    }
    private fun checkCameraPermission2(): Boolean {
        return PackageManager.PERMISSION_GRANTED ==
                ActivityCompat.checkSelfPermission(applicationContext,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
    }
}


