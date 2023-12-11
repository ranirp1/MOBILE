package cn.shef.msc5.todo.base.component.topbar

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import cn.shef.msc5.todo.utilities.GeneralUtil

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 11/11/2023 00:41
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTopAppBar(
    title: String,
    editOnClick: () -> Unit = {},
    deleteOnClick: () -> Unit = {},
    showNavigationIcon: Boolean = false,
    showFirstIcon: Boolean = false,
    showSecondIcon: Boolean = false,
    navigationIIcon: ImageVector = Icons.Filled.Close,
    firstIcon: ImageVector = Icons.Default.Edit,
    secondIcon: ImageVector = Icons.Default.Delete
) {
    val context = LocalContext.current
    TopAppBar(
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = {
                    if(navigationIIcon == Icons.Filled.ArrowBack){
                        GeneralUtil.finishActivitySlideOut(context)
                    }else{
                        GeneralUtil.finishActivitySlideDown(context)
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

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNormalTopAppBar() {
    NormalTopAppBar(title = "NormalTopAppBar")
}
