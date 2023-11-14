package cn.shef.msc5.todo.base.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cn.shef.msc5.todo.utilities.GeneralUtil

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 11/11/2023 00:40
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBar(
    title: String,
    editOnClick: () -> Unit = {},
    deleteOnClick: () -> Unit = {},
    showNavigationIcon: Boolean = false,
    showEditIcon: Boolean = false,
    showDeleteIcon: Boolean = false
) {
    val context = LocalContext.current
    androidx.compose.material3.LargeTopAppBar(
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = {
                    GeneralUtil.finishActivity(context)
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                    )
                }
            }
        },
        actions = {
            if (showEditIcon) {
                IconButton(onClick = editOnClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit ToDos"
                    )
                }
            }
            if(showDeleteIcon) {
                IconButton(onClick = deleteOnClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete ToDos"
                    )
                }
            }
        }
    )
}