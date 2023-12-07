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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.DetailActivity
import cn.shef.msc5.todo.base.component.BaseFloatingActionBar
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.ItemHolder
import cn.shef.msc5.todo.base.component.SortingMenu
import cn.shef.msc5.todo.base.component.TopBarType
import cn.shef.msc5.todo.model.isEmpty
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.ui.view.state.EmptyScreen
import cn.shef.msc5.todo.ui.view.state.LoadingScreen
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

    val isVisible = rememberSaveable { mutableStateOf(true) }
    val state = mainViewModel.state

    var fabVisibleAddTask by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    BaseScaffold(
        showTopBar = true,
        topBarType = TopBarType.SEARCH,
        title = stringResource(R.string.todo_list),
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
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            SortingMenu(sortType) {
                mainViewModel.sortAllTasks(it)
            }

            if (state.isLoading) {
                LoadingScreen()
            } else if (state.isEmpty) {
                EmptyScreen(context = context)
                isVisible.value = false
            } else {
                isVisible.value = true
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    items(
                        items = taskListState.value,
                        key = { taskItem -> taskItem.id },
                        itemContent = { item ->
                            val currentItem by rememberUpdatedState(item)
                            ItemHolder(currentItem, mainViewModel)
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    )
                }
            }
        }
    }
}
