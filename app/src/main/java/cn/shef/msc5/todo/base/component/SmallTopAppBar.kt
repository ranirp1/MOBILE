package cn.shef.msc5.todo.base.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 11/11/2023 00:41
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBar(
    title: String,
    topAppBarBack: () -> Unit = {},
    showNavigationIcon: Boolean = false
) {
    TopAppBar(
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = topAppBarBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit ToDos"
                )
            }
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete ToDos"
                )
            }
        }
    )
}