package cn.shef.msc5.todo.base.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.base.component.topbar.CancelTopAppBar
import cn.shef.msc5.todo.base.component.topbar.CenterTopAppBar
import cn.shef.msc5.todo.base.component.topbar.NormalTopAppBar

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 02/11/2023 08:07
 */
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBarType: TopBarType = TopBarType.NORMAL,
    hostState: SnackbarHostState,
    showTopBar: Boolean = false,
    showNavigationIcon: Boolean = false,
    showFirstIcon: Boolean = false,
    showSecondIcon: Boolean = false,
    title: String = stringResource(R.string.app_name),
    bottomBar: @Composable () -> Unit = {},
    firstOnClick: () -> Unit = {},
    secondOnClick: () -> Unit = {},
    firstIcon: ImageVector = Icons.Default.Edit,
    secondIcon: ImageVector = Icons.Default.Delete,
    floatingActionButton: @Composable (() -> Unit) = {},
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            if (showTopBar) {
                when (topBarType) {
                    is TopBarType.CENTER -> CenterTopAppBar(
                        title, firstOnClick, secondOnClick,
                        showNavigationIcon, showFirstIcon, showSecondIcon, firstIcon, secondIcon
                    )
                    is TopBarType.NORMAL -> NormalTopAppBar(
                        title, firstOnClick, secondOnClick,
                        showNavigationIcon, showFirstIcon, showSecondIcon, firstIcon, secondIcon
                    )
                    is TopBarType.CANCEL -> CancelTopAppBar(
                        title, firstOnClick, secondOnClick,
                        showNavigationIcon, showFirstIcon, showSecondIcon, firstIcon, secondIcon
                    )
                    else -> {}
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

sealed class TopBarType {
    object NORMAL : TopBarType()
    object CENTER : TopBarType()
    object CANCEL : TopBarType()
}