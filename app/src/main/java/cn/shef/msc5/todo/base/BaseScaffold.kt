package cn.shef.msc5.todo.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cn.shef.msc5.todo.utilities.Constants
import cn.shef.msc5.todo.base.component.LargeTopAppBar
import cn.shef.msc5.todo.base.component.MediumTopAppBar
import cn.shef.msc5.todo.base.component.SmallTopAppBar

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 02/11/2023 08:07
 */
@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    topBarSize: TopBarSize = TopBarSize.SMALL,
    showTopBar: Boolean = false,
    showNavigationIcon: Boolean = false,
    title: String = Constants.APP_NAME,
    bottomBar: @Composable () -> Unit = {},
    //currently scaffold do not manage the state of the snackbar's state
    snackBarHost: @Composable () -> Unit = {},
//    snackBarHost: @Composable () -> Unit = {
//        BaseSnackBar(
//            snackBarEnum = SnackBarColorEnum.SUCCESS
//        )
//    },
    topAppBarBack: () -> Unit = {},
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
                    is TopBarSize.LARGE -> LargeTopAppBar(title, topAppBarBack, showNavigationIcon);
                    is TopBarSize.MEDIUM -> MediumTopAppBar(title, topAppBarBack, showNavigationIcon);
                    is TopBarSize.SMALL -> SmallTopAppBar(title, topAppBarBack, showNavigationIcon);
                }
            }
        },
        bottomBar = bottomBar,
        snackbarHost = snackBarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.End,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
    ) { innerPadding ->
        content(innerPadding)
    }
}

sealed class TopBarSize {
    object SMALL : TopBarSize()
    object MEDIUM : TopBarSize()
    object LARGE : TopBarSize()
}