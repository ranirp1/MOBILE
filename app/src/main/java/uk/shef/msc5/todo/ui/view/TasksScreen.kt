package uk.shef.msc5.todo.ui.view

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import uk.shef.msc5.todo.R
import uk.shef.msc5.todo.base.component.BaseFloatingActionBar
import uk.shef.msc5.todo.base.component.BaseScaffold
import uk.shef.msc5.todo.base.component.TopBarType
import uk.shef.msc5.todo.model.viewmodel.MainViewModel
import uk.shef.msc5.todo.utilities.GeneralUtil

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@Composable
fun TasksScreen(context: Context, mainViewModel: MainViewModel) {

    var fabVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = false,
        showSecondIcon = true,
        topBarType = TopBarType.NORMAL,
        secondIcon = Icons.Default.Sort,
        title = stringResource(R.string.todo_tasks),
        floatingActionButton = {
            BaseFloatingActionBar(
                fabVisible = fabVisible,
                onClick = {
                    val intent = Intent(context, uk.shef.msc5.todo.activity.DetailActivity::class.java)
                    GeneralUtil.startActivity2(context, intent)
                }
            )
        },
        hostState = snackbarHostState) {

    }

}
