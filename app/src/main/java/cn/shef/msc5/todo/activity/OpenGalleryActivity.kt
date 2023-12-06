package cn.shef.msc5.todo.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class OpenGalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var galleryImageUri by remember { mutableStateOf<Uri?>(null) }
            var galleryLaunched by remember { mutableStateOf(false) }

            val imageFromGalleryLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia()
            ) { imageChosen ->
                if (imageChosen == null) {
                    galleryImageUri = null
                } else {
                    galleryImageUri = imageChosen
                    val resultIntent = Intent().apply {
                        putExtra("capturedImageUri", galleryImageUri.toString())
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            }

            LaunchedEffect(galleryLaunched) {
                if (!galleryLaunched) {
                    imageFromGalleryLauncher.launch(
                        PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                    galleryLaunched = true
                }
            }
        }
    }
}
