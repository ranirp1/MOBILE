package cn.shef.msc5.todo.base.component.dialog

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.activity.CaptureImageActivity
import cn.shef.msc5.todo.activity.OpenGalleryActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageBottomSheet(
    onCapturedImageUri: (Uri?) -> Unit,
    onSelect: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val capturedImageUriString = data?.getStringExtra("capturedImageUri")
            imageUri = Uri.parse(capturedImageUriString)
            onCapturedImageUri(imageUri)
            onSelect(false)
        }
    }

    ModalBottomSheet(
        modifier = Modifier.height(200.dp),
        onDismissRequest = { onSelect(false) }
    ) {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    val intent = Intent(context, CaptureImageActivity::class.java)
                    activityResultLauncher.launch(intent)
                }
            }
        ) {
            Text(text = "Open camera")
        }

        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    val intent = Intent(context, OpenGalleryActivity::class.java)
                    activityResultLauncher.launch(intent)
                }
            }) {
            Text(text = "Choose from gallery")
        }
    }
}
