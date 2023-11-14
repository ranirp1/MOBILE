package cn.shef.msc5.todo.ui.screen

import android.content.res.Configuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.base.BaseScaffold

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 13:28
 */
@Composable
fun DetailScreen() {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = true,
        title = stringResource(R.string.todo_title),
        hostState = snackbarHostState
    ) {

    }
}


@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailScreen() {
//    MainScreen(LocalContext.current)
}
