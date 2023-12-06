package cn.shef.msc5.todo.activity

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

class OpenGalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var pickedImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
            val imageFromGalleryLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.PickVisualMedia()
            ) { uri: Uri? ->
                if (uri == null) {
                    pickedImageBitmap = null
                } else {
                    pickedImageBitmap = ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            contentResolver, uri)).asImageBitmap()
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
                    }
                }
            }
        }
    }
}
