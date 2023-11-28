package cn.shef.msc5.todo.base.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 17/11/2023 10:55
 */

@Composable
fun ConfirmDialog(
    titleText: String = "Delete Todo",
    contentText: String = "Once deleted the todo cannot be retrieved.",
    confirmText: String = "Delete",
    dismissText: String = "Cancel",
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
                    // Todo delete task
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