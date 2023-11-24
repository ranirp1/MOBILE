package uk.shef.msc5.todo.base.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import uk.shef.msc5.todo.utilities.GeneralUtil

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