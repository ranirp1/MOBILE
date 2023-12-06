package cn.shef.msc5.todo.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.GeoLocationActivity
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.bottombar.BottomActionBar
import cn.shef.msc5.todo.base.component.dialog.ImageBottomSheet
import cn.shef.msc5.todo.utilities.GeneralUtil
import cn.shef.msc5.todo.utilities.ImageUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 13:28
 */
@SuppressLint("UnrememberedMutableState")
@ExperimentalAnimationApi
@Composable
fun DetailScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    val imageUtil = ImageUtil()

    var isSheetOpen by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
    var cameraImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = true,
        showFirstIcon = false,
        showSecondIcon = false,
        title = stringResource(R.string.todo_new_task),
        hostState = snackbarHostState, bottomBar = {
            BottomActionBar(modifier = Modifier.height(70.dp),
                title = "Save",
                onCamera = {
                    isSheetOpen = true
                },
                onLocation = {
                    //GeneralUtil.finishActivity2(context)
                    val intent = Intent(context, GeoLocationActivity::class.java)
                    GeneralUtil.startActivity2(context, intent)
                },
                onSubTask = {
                    scope.launch {

                    }
                },
                onCalender = {
                    scope.launch {
                        snackbarHostState.showSnackbar("onCalender")
                    }
                },
                onPriority = {
                    scope.launch {
                        scope.launch {
                            snackbarHostState.showSnackbar("Level")
                        }
                    }
                },
                addClick = {
                    GeneralUtil.finishActivity2(context)
                })
        }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
            //.heightIn(80.dp)
            , verticalArrangement = Arrangement.Top
        ) {

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .heightIn(80.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp),
                value = title,
                singleLine = true,
                label = { Text(text = "Title") },
                onValueChange = { title = it })

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .heightIn(220.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp), value = text,
                maxLines = 10,
                minLines = 1,
                label = { Text(text = "Description") },
                onValueChange = { text = it })

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp)
                    .padding(10.dp), text = "Selected Task Location is "
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(10.dp)
                    .padding(10.dp), text = "Due Date:"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(10.dp)
                    .padding(10.dp), text = "Due time:"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                capturedImageUri?.let { uri ->
                    cameraImageBitmap = imageUtil.getImageBitmap(contentResolver, uri)
                    Image(bitmap = cameraImageBitmap!!, contentDescription = "Captured Image")
                }
            }
        }
    }

    //Capture Image
    if (isSheetOpen) {
        ImageBottomSheet(
            onCapturedImageUri = { capturedImageUri = it },
            onSelect = {isSheetOpen = it}
        )
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailScreen() {
//    MainScreen(LocalContext.current)
}