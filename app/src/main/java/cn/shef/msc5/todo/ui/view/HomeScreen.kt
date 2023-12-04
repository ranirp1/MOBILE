package cn.shef.msc5.todo.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
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
import cn.shef.msc5.todo.model.PriorityLevelEnum
import cn.shef.msc5.todo.model.isEmpty
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.ui.view.state.EmptyScreen
import cn.shef.msc5.todo.ui.view.state.LoadingScreen
import cn.shef.msc5.todo.utilities.AppInfoUtil
import cn.shef.msc5.todo.utilities.GeneralUtil
import java.util.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@Composable
fun HomeScreen(context: Context, mainViewModel: MainViewModel) {

    val taskListState = mainViewModel.taskListFlow.collectAsState(listOf())

    val sortType = mainViewModel.sortType

    val snackbarHostState = remember { SnackbarHostState() }

    var date by remember { mutableStateOf(Date()) }

    val listState = rememberLazyListState()

    val isVisible = rememberSaveable { mutableStateOf(true) }

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

    val state = mainViewModel.state

    BaseScaffold(
        showTopBar = true,
        topBarType = TopBarType.SEARCH,
        title = stringResource(R.string.todo_title),
        floatingActionButton = {
            AnimatedVisibility(
                visible = isVisible.value,
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                Column {
                    BaseFloatingActionBar(
                        imageVector = Icons.Default.PinDrop,
                        contentDescription = "Location",
                        onClick = {
//                        // TODO check todos by location
                           AppInfoUtil.test(mainViewModel)
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    BaseFloatingActionBar(
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
        //switch the state page
        if (state.isLoading) {
            LoadingScreen()
        } else if (state.isEmpty) {
            EmptyScreen(context = context)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.background)
                    .nestedScroll(nestedScrollConnection),
                state = listState,
            ) {
                stickyHeader {
                    DatePickerBar(
                        onDateSelected = { date = it }
                    )
                    SortingMenu(sortType) {
                        // TODO change to date specific getAllTask
                        mainViewModel.sortAllTasks(it)
                    }
                }
                items(
                    items = taskListState.value,
                    key = { taskItem -> taskItem.id },
                    itemContent = { item ->
                        Row(Modifier.animateItemPlacement(
                            tween(durationMillis = 250)
                        )) {
                            val currentItem by rememberUpdatedState(item)
                            if(PriorityLevelEnum.LOW.id == currentItem.priority)
                                ItemHolder(context, currentItem, mainViewModel, PriorityLevelEnum.LOW.color)
                            else if(PriorityLevelEnum.MEDIUM.id == currentItem.priority)
                                ItemHolder(context, currentItem, mainViewModel, PriorityLevelEnum.MEDIUM.color)
                            else if(PriorityLevelEnum.HIGH.id == currentItem.priority)
                                ItemHolder(context, currentItem, mainViewModel, PriorityLevelEnum.HIGH.color)
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                )

                // avoid over-lapping with bottom navigation bar
                item {
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }

        }

    }
}

