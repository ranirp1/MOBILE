package cn.shef.msc5.todo.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.height
import androidx.compose.material.SnackbarResult
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.DetailActivity
import cn.shef.msc5.todo.activity.MapsActivity
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.bottombar.BottomActionBar
import cn.shef.msc5.todo.demos.ui.timepickers.TimePickerDialog
import cn.shef.msc5.todo.demos.ui.timepickers.TimePickersActivity
import cn.shef.msc5.todo.utilities.GeneralUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 13:28
 */
@ExperimentalAnimationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen() {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope: CoroutineScope = rememberCoroutineScope()

    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = true,
        showFirstIcon = false,
        showSecondIcon = false,
        title = stringResource(R.string.todo_new_task),
        hostState = snackbarHostState,
        bottomBar = {
            BottomActionBar(modifier = Modifier.height(70.dp),
                title = "Save",
                onCamera = {
                    scope.launch {
                        snackbarHostState.showSnackbar("onCamera")
                    }
                },
                onLocation = {
                    scope.launch {
                        snackbarHostState.showSnackbar("onLocation")
                        val intent = Intent(context, MapsActivity::class.java)
                        GeneralUtil.startActivity2(context, intent)
                    }
                },
                onSubTask = {
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "onSubTask",
                            actionLabel = "back")
                        if(result == androidx.compose.material3.SnackbarResult.ActionPerformed) {
                            Log.d("123", "DetailScreen: aaa");
                        }
                    }
                },
                addClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Add task success",
                            duration = SnackbarDuration.Short)
                        GeneralUtil.finishActivity2(context)
                    }
                })
        },
    ) {

    }
}


@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailScreen() {
//    MainScreen(LocalContext.current)
}
