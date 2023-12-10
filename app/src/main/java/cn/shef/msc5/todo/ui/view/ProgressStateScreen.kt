package cn.shef.msc5.todo.ui.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.DetailActivity
import cn.shef.msc5.todo.base.component.BaseFloatingActionBar
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.PriorityTab
import cn.shef.msc5.todo.base.component.TopBarType
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.utilities.GeneralUtil

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@Composable
fun ProgressStateScreen(context: Context,
                        progressUnfinishedViewModel: MainViewModel,
                        progressIsCompletedViewModel: MainViewModel) {

    var fabVisibleAddTask by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    BaseScaffold(
        showTopBar = true,
        topBarType = TopBarType.NORMAL,
        title = stringResource(R.string.todo_progress),
        floatingActionButton = {
            Column {
                BaseFloatingActionBar(
                    fabVisible = fabVisibleAddTask,
                    onClick = {
                        val intent = Intent(context, DetailActivity::class.java)
                        GeneralUtil.startActivity2(context, intent)
                    }
                )
            }
        },
        hostState = snackbarHostState
    ) {
        PriorityTab(context, progressUnfinishedViewModel, progressIsCompletedViewModel)
    }
}
