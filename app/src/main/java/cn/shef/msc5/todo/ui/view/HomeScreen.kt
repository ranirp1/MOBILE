package cn.shef.msc5.todo.ui.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.MutableState
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
import cn.shef.msc5.todo.base.component.DatePickerBar
import cn.shef.msc5.todo.base.component.ItemHolder
import cn.shef.msc5.todo.base.component.SortingMenu
import cn.shef.msc5.todo.base.component.TopBarType
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.utilities.GeneralUtil
import java.time.LocalDate

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(context: Context, mainViewModel: MainViewModel) {

    val taskListState = mainViewModel.taskListFlow.collectAsState(listOf())
    val sortType = mainViewModel.sortType

    var fabVisibleAddTask by remember { mutableStateOf(false) }
    var fabVisibleLocation by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    var date by remember { mutableStateOf(mainViewModel.date) }

    BaseScaffold(
        showTopBar = true,
        topBarType = TopBarType.SEARCH,
        title = stringResource(R.string.todo_title),
        floatingActionButton = {
            Column {
                BaseFloatingActionBar(
                    fabVisible = fabVisibleLocation,
                    imageVector = Icons.Default.PinDrop,
                    contentDescription = "Location",
                    onClick = {
                        // TODO check todos by location
                        mainViewModel.addTask("title", "description", 1, 1.11F, 1.11F,
                        "imageUrl", java.sql.Date.valueOf(LocalDate.now().toString()), java.sql.Date.valueOf(
                            LocalDate.now().toString()),
                        java.sql.Date.valueOf(LocalDate.now().toString()),
                        0,"remark", null)
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
        Column {
            DatePickerBar(
                onDateSelected = {
                    date = it
                    mainViewModel.sortTasksByDate(sortType, it)
                }
            )
            SortingMenu(sortType) {
                mainViewModel.sortTasksByDate(it, date)
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 25.dp),
            ) {

                items(
                    items = taskListState.value,
                    key = { taskItem -> taskItem.id },
                    itemContent = { item ->
                        val currentItem by rememberUpdatedState(item)
                        Spacer(modifier = Modifier.height(10.dp))
                        ItemHolder(currentItem)
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                )
            }
        }
    }
}

