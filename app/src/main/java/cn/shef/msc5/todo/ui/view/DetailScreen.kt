package cn.shef.msc5.todo.ui.view

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.CaptureImageActivity
import cn.shef.msc5.todo.activity.LocationActivity
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.bottombar.BottomActionBar
import cn.shef.msc5.todo.utilities.GeneralUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 13:28
 */
@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalAnimationApi
@Composable
fun DetailScreen() {
    var text by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope: CoroutineScope = rememberCoroutineScope()
    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = true,
        showFirstIcon = false,
        showSecondIcon = false,
        title = stringResource(R.string.todo_new_task),
        hostState = snackbarHostState,bottomBar = {
            BottomActionBar(modifier = Modifier.height(70.dp),
                title = "Save",
                onCamera = {
                    scope.launch {
                        snackbarHostState.showSnackbar("onCamera")
                        val intent = Intent(context, CaptureImageActivity::class.java)
                        GeneralUtil.startActivity2(context, intent)
                    }
                },
                onLocation = {
                    GeneralUtil.finishActivity2(context)
//                    scope.launch {
//                        snackbarHostState.showSnackbar("Loc")
//                    }
                },
                onSubTask = {
                    scope.launch {
                    }
                },
                onCalender = {scope.launch {
                    snackbarHostState.showSnackbar("onCalender")
                }},
                onPriority={scope.launch {
                    scope.launch {
                        snackbarHostState.showSnackbar("Level")
                    }
                }},
                addClick = {
                    GeneralUtil.finishActivity2(context)
//                    scope.launch {
////                        snackbarHostState.showSnackbar("Add task success",
////                            duration = SnackbarDuration.Short)
//                        GeneralUtil.finishActivity2(context)
//                    }
                })
        }) {

        Column(modifier = Modifier
            .fillMaxWidth()
            //.heightIn(80.dp)
            ,verticalArrangement = Arrangement.Top) {

            TextField(modifier = Modifier
                .fillMaxWidth()
                .heightIn(80.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp),
                value = title,
                onValueChange = { title = it },
                placeholder = { Text(text = "Title") })

            TextField(modifier = Modifier
                .fillMaxWidth()
                .heightIn(500.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp), value = text,
                onValueChange = { text = it },
                placeholder = { Text(text = "Description") })
            Text(modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp)
                .padding(10.dp), text = "Selected Task Location is ")
            Text(modifier = Modifier
                .fillMaxWidth()
                .heightIn(10.dp)
                .padding(10.dp), text = "Due Date:")
            Text(modifier = Modifier
                .fillMaxWidth()
                .heightIn(10.dp)
                .padding(10.dp), text = "Due time:")
        }
    }
}


@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailScreen() {
//    MainScreen(LocalContext.current)
}

