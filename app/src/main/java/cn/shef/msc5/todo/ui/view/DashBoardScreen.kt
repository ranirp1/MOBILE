package cn.shef.msc5.todo.ui.view

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.base.component.AppScaffold
import cn.shef.msc5.todo.model.viewmodel.MainViewModel

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@Composable
fun DashBoardScreen(context: Context, mainViewModel: MainViewModel) {

    val snackbarHostState = remember { SnackbarHostState() }
    AppScaffold(
        showTopBar = true,
        title = stringResource(R.string.todo_dashboard),
        hostState = snackbarHostState) {
    }

}
