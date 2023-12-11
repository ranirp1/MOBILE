package cn.shef.msc5.todo.base.component.dialog

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.CaptureActivity
import cn.shef.msc5.todo.activity.OpenGalleryActivity
import cn.shef.msc5.todo.base.component.bottombar.BottomConfirmBar
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

    var cameraActivity by remember { mutableStateOf(true) }

    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val capturedImageUriString = data?.getStringExtra("capturedImageUri")
            imageUri = Uri.parse(capturedImageUriString)
            if(!cameraActivity){
                imageUri?.let { uri ->
                    context.contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION )
                }
            }
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
                    val intent = Intent(context, CaptureActivity::class.java)
                    activityResultLauncher.launch(intent)
                }
            }
        ) {
            Text(text = stringResource(R.string.detail_open_camera))
        }

        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                cameraActivity = false
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    val intent = Intent(context, OpenGalleryActivity::class.java)
                    activityResultLauncher.launch(intent)
                }
            }) {
            Text(text = stringResource(R.string.detail_choose_from_gallery))
        }
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewImageBottomSheet() {
    ImageBottomSheet(
        onCapturedImageUri = {
        },
        onSelect = {
        }
    )
}
