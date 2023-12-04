package cn.shef.msc5.todo.base.component.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import cn.shef.msc5.todo.utilities.GeneralUtil

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 11/11/2023 00:40
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(
    title: String,
    firstOnClick: () -> Unit = {},
    secondOnClick: () -> Unit = {},
    showNavigationIcon: Boolean = false,
    showFirstIcon: Boolean = false,
    showSecondIcon: Boolean = false,
    navigationIIcon: ImageVector = Icons.Filled.Close,
    firstIcon: ImageVector = Icons.Default.Edit,
    secondIcon: ImageVector = Icons.Default.Delete
) {
    val context = LocalContext.current
    CenterAlignedTopAppBar(
//        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//            containerColor =  MaterialTheme.colorScheme.primary),
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = {
                    if(navigationIIcon == Icons.Filled.ArrowBack){
                        GeneralUtil.finishActivity(context)
                    }else{
                        GeneralUtil.finishActivity2(context)
                    }
                }) {
                    Icon(
                        imageVector = navigationIIcon,
                        contentDescription = "",
                    )
                }
            }
        },
        actions = {
            if (showFirstIcon) {
                IconButton(onClick = firstOnClick) {
                    Icon(
                        imageVector = firstIcon,
                        contentDescription = "Edit ToDos"
                    )
                }
            }
            if(showSecondIcon) {
                IconButton(onClick = secondOnClick) {
                    Icon(
                        imageVector = secondIcon,
                        contentDescription = "Delete ToDos"
                    )
                }
            }
        }
    )
}