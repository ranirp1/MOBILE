package uk.shef.msc5.todo.ui.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uk.shef.msc5.todo.R
import uk.shef.msc5.todo.base.component.BaseScaffold
import uk.shef.msc5.todo.model.viewmodel.MainViewModel

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */

@Composable
internal fun DashBoardScreen(context: Context, mainViewModel: MainViewModel) {

    var inputText by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = false,
        showFirstIcon = false,
        showSecondIcon = false,
        title = stringResource(R.string.todo_dashboard),
        hostState = snackbarHostState) {
//        DatePicker()
        TextField(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp),
            value = inputText,
            placeholder = {
                Text("type message here")
            },
            onValueChange = {
                inputText = it
            },
            trailingIcon = {
                if (inputText.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Text("Send")
                    }
                }
            }
        )

    }

}
