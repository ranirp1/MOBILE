package cn.shef.msc5.todo.ui.view

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
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
@ExperimentalAnimationApi
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
                    }
                },
                onSubTask = {
                    scope.launch {
                        snackbarHostState.showSnackbar("onSubTask")
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
