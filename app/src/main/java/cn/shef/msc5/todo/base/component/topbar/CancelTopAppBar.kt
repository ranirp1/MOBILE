package cn.shef.msc5.todo.base.component.topbar

import android.util.Log
import androidx.compose.material.icons.Icons
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
fun CancelTopAppBar(
    title: String,
    editOnClick: () -> Unit = {},
    deleteOnClick: () -> Unit = {},
    showNavigationIcon: Boolean = false,
    showFirstIcon: Boolean = false,
    showSecondIcon: Boolean = false,
    firstIcon: ImageVector = Icons.Default.Edit,
    secondIcon: ImageVector = Icons.Default.Delete
) {
    Log.d("CancelTopAppBar", "CancelTopAppBar: ")
    val context = LocalContext.current
    CenterAlignedTopAppBar(
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = {
                    GeneralUtil.finishActivity2(context)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                    )
                }
            }
        },
        actions = {
            if (showFirstIcon) {
                IconButton(onClick = editOnClick) {
                    Icon(
                        imageVector = firstIcon,
                        contentDescription = "Edit ToDos"
                    )
                }
            }
            if(showSecondIcon) {
                IconButton(onClick = deleteOnClick) {
                    Icon(
                        imageVector = secondIcon,
                        contentDescription = "Delete ToDos"
                    )
                }
            }
        }
    )
}