package cn.shef.msc5.todo.base.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.model.dto.SubTask

@Composable
fun CheckboxListTextField(
    items: List<SubTask>,
    isEdit: Boolean,
    onItemCheckedChange: (Int, Boolean) -> Unit,
    onItemTextChange: (Int, String) -> Unit,
    onAddItemClick: () -> Unit,
    onDeleteItemClick: (Int) -> Unit
) {
    for (index in items.indices) {
        CheckboxRow(
            item = items[index],
            isEdit = isEdit,
            onItemCheckedChange = { isChecked ->
                if(isEdit) onItemCheckedChange(index, isChecked)
                else {}
            },
            onItemTextChange = { newText ->
                if(isEdit) onItemTextChange(index, newText)
                else {}
            },
            onDeleteItemClick = {
                onDeleteItemClick(index)
            }
        )
    }

    // Add item row
    if(isEdit){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "Add Subtask")

            IconButton(
                onClick = {
                    onAddItemClick()
                }
            ) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Item")
            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CheckboxRow(
    item: SubTask,
    isEdit: Boolean,
    onItemCheckedChange: (Boolean) -> Unit,
    onItemTextChange: (String) -> Unit,
    onDeleteItemClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox
        Checkbox(
            checked = item.isChecked,
            onCheckedChange = { isChecked ->
                onItemCheckedChange(isChecked)
            },
            modifier = Modifier
                .padding(end = 8.dp)
        )

        // Editable item text
        BasicTextField(
            value = item.text,
            onValueChange = { newText ->
                onItemTextChange(newText)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Additional logic when editing is done
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    // Toggle the checkbox state when the text is clicked
                    onItemCheckedChange(!item.isChecked)
                }
        )

        // Delete button
        if(isEdit){
            IconButton(
                onClick = onDeleteItemClick
            ) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete Item")
            }
        }
    }
}

@Composable
fun CheckboxListTextFieldList(
    subTasks: List<SubTask>,
    isEdit: Boolean,
    onChangeSubTaskList: (List<SubTask>) -> Unit
) {
    var checkboxItems by remember { mutableStateOf(subTasks) }

    CheckboxListTextField(
        items = checkboxItems,
        isEdit = isEdit,
        onItemCheckedChange = { index, isChecked ->
            checkboxItems = checkboxItems.toMutableList().also {
                it[index] = it[index].copy(isChecked = isChecked)
            }
            onChangeSubTaskList(checkboxItems)
        },
        onItemTextChange = { index, newText ->
            checkboxItems = checkboxItems.toMutableList().also {
                it[index] = it[index].copy(text = newText)
            }
            onChangeSubTaskList(checkboxItems)
        },
        onAddItemClick = {
            checkboxItems = checkboxItems.toMutableList().apply {
                add(SubTask(""))
            }
            onChangeSubTaskList(checkboxItems)
        },
        onDeleteItemClick = { index ->
            checkboxItems = checkboxItems.toMutableList().also {
                it.removeAt(index)
            }
            onChangeSubTaskList(checkboxItems)
        }
    )
}