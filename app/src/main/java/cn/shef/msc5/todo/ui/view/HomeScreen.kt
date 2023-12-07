package cn.shef.msc5.todo.ui.view

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import cn.shef.msc5.todo.base.component.dialog.ConfirmDialog
import cn.shef.msc5.todo.model.isEmpty
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.ui.view.state.EmptyScreen
import cn.shef.msc5.todo.ui.view.state.LoadingScreen
import cn.shef.msc5.todo.utilities.DateConverter
import cn.shef.msc5.todo.utilities.GeneralUtil

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(context: Context, mainViewModel: MainViewModel) {

    val dateConverter = DateConverter()
    val taskListState = mainViewModel.taskListFlow.collectAsState(listOf())
    var sortType by remember { mutableStateOf(mainViewModel.sortType) }

    var fabVisibleAddTask by remember { mutableStateOf(false) }
    var fabVisibleLocation by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    var date by remember { mutableStateOf(mainViewModel.date) }
    var numTaskDue by remember { mutableIntStateOf(0) }

    var snackbarShown by remember { mutableStateOf(mainViewModel.notInitial) }
    var showNoticeDialog by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    val isVisible = rememberSaveable { mutableStateOf(true) }
    val state = mainViewModel.state

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -1) {
                    isVisible.value = false
                }
                if (available.y > 1) {
                    isVisible.value = true
                }
                return Offset.Zero
            }
        }
    }

    if(!state.isEmpty && !snackbarShown){
        LaunchedEffect(Unit) {
            if (!snackbarShown) {
                val currentTime = System.currentTimeMillis()
                for (task in taskListState.value) {
                    val taskDueTime = dateConverter.converterDate(task.dueTime)

                    if (currentTime > taskDueTime && task.state != 2) {
                        numTaskDue++
                    }
                }

                if (numTaskDue > 0) {
                    showNoticeDialog = true
                    snackbarShown = true
                }
            }
        }
        mainViewModel.notInitialLaunch()
    }


    BaseScaffold(
        showTopBar = true,
        topBarType = TopBarType.NORMAL,
        title = stringResource(R.string.todo_title),
        floatingActionButton = {
            AnimatedVisibility(
                visible = isVisible.value,
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
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
            }
        },
        hostState = snackbarHostState
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            DatePickerBar(
                onDateSelected = {
                    date = it
                    mainViewModel.sortTasksByDate(sortType, it)
                }
            )
            SortingMenu(sortType) {
                mainViewModel.sortTasksByDate(it, date)
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
                        .background(MaterialTheme.colorScheme.background)
                        .nestedScroll(nestedScrollConnection),
                    state = listState,
                ) {

                    items(
                        items = taskListState.value,
                        key = { taskItem -> taskItem.id },
                        itemContent = { item ->
                            val currentItem by rememberUpdatedState(item)
//                            Spacer(modifier = Modifier.height(10.dp))
                            ItemHolder(currentItem, mainViewModel)
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    )
                    // avoid over-lapping with bottom navigation bar
                    item {
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }

        if(showNoticeDialog){
            ConfirmDialog(
                titleText = "Notice",
                contentText = "You have $numTaskDue task${if (numTaskDue > 1) "s" else ""} due!",
                confirmText = "Ok",
                dismissText = ""
            ){
                showNoticeDialog = it
            }
        }
    }
}

