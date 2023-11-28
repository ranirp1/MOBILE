package cn.shef.msc5.todo.ui.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.DetailActivity
import cn.shef.msc5.todo.base.component.BaseFloatingActionBar
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.ItemHolder
import cn.shef.msc5.todo.base.component.SortingMenu
import cn.shef.msc5.todo.base.component.TopBarType
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.utilities.GeneralUtil

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@Composable
fun TasksScreen(context: Context, mainViewModel: MainViewModel) {
    val taskListState = mainViewModel.taskListFlow.collectAsState(listOf())
    val sortType = mainViewModel.sortType

    var fabVisibleAddTask by remember { mutableStateOf(false) }
    var fabVisibleLocation by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    BaseScaffold(
        showTopBar = true,
        topBarType = TopBarType.SEARCH,
        title = stringResource(R.string.todo_tasks),
        floatingActionButton = {
            Column {
                BaseFloatingActionBar(
                    fabVisible = fabVisibleLocation,
                    imageVector = Icons.Default.PinDrop,
                    contentDescription = "Location",
                    onClick = {
                        // TODO check todos by location
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background),
        ) {
            item {
                SortingMenu(sortType) {
                    mainViewModel.sortAllTasks(it)
                }
            }

            // trying to display some itemholders
            item {
                Column(
                    modifier = Modifier.padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    (1..10).forEach {
                        ItemHolder()
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
            }

            items(
                items = taskListState.value,
                key = { taskItem -> taskItem.id },
                itemContent = { item ->
                    val currentItem by rememberUpdatedState(item)
                    Text(text = "title: ${item.title}")
                    Text(text = "level: ${item.priority}")
                    Text(text = "remark: ${item.remark}")
                    Divider(color = Color.Blue)
                }
            )
            // Avoid over-lapping with bottom navigation bar
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }

}
