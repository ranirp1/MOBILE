@file:OptIn(ExperimentalFoundationApi::class)

package cn.shef.msc5.todo.ui.view

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DonutSmall
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.model.database.AppDatabase
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.model.viewmodel.MainViewModelFactory
import cn.shef.msc5.todo.utilities.Constants

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 05/11/2023 22:56
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {

    val mainViewModel by lazy {
        MainViewModelFactory(AppDatabase.INSTANCE.getTaskDAO()).
        create(MainViewModel::class.java)
    }

    //get context
    val items = listOf(
        Constants.NAVIGATION_HOME,
        Constants.NAVIGATION_PROGRESS,
        Constants.NAVIGATION_PROFILE
    )

    var selectedItem by remember { mutableStateOf(items.first()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    BaseScaffold(
        showTopBar = false,
        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(75.dp)
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(getIconForScreen(item), contentDescription = null) },
                        label = { Text(item) },
                        selected = item == selectedItem,
                        onClick = {
                            selectedItem = item
                        },
                        alwaysShowLabel = true
                    )
                }
            }
        },
        hostState = snackbarHostState,
    ) {
        when (selectedItem) {
            Constants.NAVIGATION_HOME -> HomeScreen(context, mainViewModel)
            Constants.NAVIGATION_PROGRESS -> ProgressScreen(context, mainViewModel)
//            Constants.NAVIGATION_TASKS -> EmptyScreen(context)
//            Constants.NAVIGATION_PROFILE -> SettingScreen(context, mainViewModel)
            Constants.NAVIGATION_PROFILE -> ProfileScreen(context, mainViewModel)
        }
    }
}


@Composable
fun getIconForScreen(items: String): ImageVector {
    return when (items) {
        Constants.NAVIGATION_HOME -> Icons.Default.Home
        Constants.NAVIGATION_PROGRESS -> Icons.Default.DonutSmall
        Constants.NAVIGATION_PROFILE -> Icons.Default.AccountCircle
        else -> Icons.Default.Home
    }
}


@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
//    MainScreen(LocalContext.current)
}
