package cn.shef.msc5.todo.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cn.shef.msc5.todo.base.component.CenterTopAppBar
import cn.shef.msc5.todo.utilities.Constants
import cn.shef.msc5.todo.base.component.NormalTopAppBar

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 02/11/2023 08:07
 */
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBarSize: TopBarSize = TopBarSize.CENTER,
    hostState: SnackbarHostState,
    showTopBar: Boolean = false,
    showNavigationIcon: Boolean = false,
    showEditIcon: Boolean = false,
    showDeleteIcon: Boolean = false,
    title: String = Constants.APP_NAME,
    bottomBar: @Composable () -> Unit = {},
    editOnClick: () -> Unit = {},
    deleteOnClick: () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            if (showTopBar) {
                when (topBarSize) {
                    is TopBarSize.CENTER -> CenterTopAppBar(title, editOnClick, deleteOnClick,
                        showNavigationIcon, showEditIcon, showDeleteIcon);
                    is TopBarSize.NORMAL -> NormalTopAppBar(title, editOnClick, deleteOnClick,
                        showNavigationIcon, showEditIcon, showDeleteIcon);
                }
            }
        },
        bottomBar = bottomBar,
        snackbarHost = {
            SnackbarHost(hostState = hostState)
        },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.End,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
    ) {
        Box(modifier = Modifier.padding(it)) {
            content(it)
        }
    }
}