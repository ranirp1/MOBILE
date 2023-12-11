package cn.shef.msc5.todo.base.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.stringResource
import cn.shef.msc5.todo.R

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 17/11/2023 10:55
 */

@Composable
fun ConfirmDialog(
    titleText: String = stringResource(R.string.confirm_delete_todo),
    contentText: String = stringResource(R.string.confirm_delete_content),
    confirmText: String = stringResource(R.string.confirm_delete),
    dismissText: String = stringResource(R.string.confirm_cancel),
    onClick: () -> Unit = {},
    showDialog: (Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { showDialog(false) },
        title = {
            Text(text = titleText)
        },
        text = {
            Text(text = contentText)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    showDialog(false)
                    onClick.invoke()
                }
            ) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { showDialog(false) }
            ) {
                Text(text = dismissText)
            }
        }
    )
}