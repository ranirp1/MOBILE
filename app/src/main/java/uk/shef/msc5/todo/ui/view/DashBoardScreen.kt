package uk.shef.msc5.todo.ui.view

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import uk.shef.msc5.todo.R
import uk.shef.msc5.todo.base.component.BaseScaffold
import uk.shef.msc5.todo.model.viewmodel.MainViewModel

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@Composable
fun DashBoardScreen(context: Context, mainViewModel: MainViewModel) {

    val snackbarHostState = remember { SnackbarHostState() }
    BaseScaffold(
        showTopBar = true,
        title = stringResource(R.string.todo_dashboard),
        hostState = snackbarHostState) {
//        DatePicker()
//        TimeInputSample()
    }

}
