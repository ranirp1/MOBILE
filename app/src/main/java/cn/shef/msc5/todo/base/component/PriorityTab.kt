package cn.shef.msc5.todo.base.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cn.shef.msc5.todo.model.enums.TaskStateEnum
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.ui.view.StateScreen

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 05/12/2023 16:34
 */
@Composable
fun PriorityTab(context: Context,
                progressUnfinishedViewModel: MainViewModel,
                progressIsCompletedViewModel: MainViewModel){
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Unfinished", "IsCompleted")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> StateScreen(context, progressUnfinishedViewModel, TaskStateEnum.UNFINISHED.levelStr)
            1 -> StateScreen(context, progressIsCompletedViewModel, TaskStateEnum.ISCOMPLETED.levelStr)
        }
    }
}