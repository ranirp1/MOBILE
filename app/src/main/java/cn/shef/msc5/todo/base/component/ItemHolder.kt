package cn.shef.msc5.todo.base.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.shef.msc5.todo.base.component.dialog.ConfirmDialog
import cn.shef.msc5.todo.ui.theme.PurpleGrey40
import cn.shef.msc5.todo.utilities.Constants.Companion.OPTIONS_DELETE
import cn.shef.msc5.todo.utilities.Constants.Companion.OPTIONS_DUPLICATE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemHolder(
    // TODO add the task variables
) {
    var showOptionsMenu by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        onClick = {
            // TODO go to edit page
        }
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Title", fontSize = 20.sp)
                Box {
                    IconButton(
                        modifier = Modifier.size(20.dp),
                        onClick = {
                            // TODO delete/copy
                            showOptionsMenu = !showOptionsMenu
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Task actions"
                        )
                    }
                    DropdownMenu(
                        expanded = showOptionsMenu,
                        onDismissRequest = { showOptionsMenu = false }
                    ) {
                        DropdownMenuItem(
                            leadingIcon = { Icon(imageVector = Icons.Default.ContentCopy, contentDescription = OPTIONS_DUPLICATE)},
                            text = { Text(text = OPTIONS_DUPLICATE, color = PurpleGrey40) },
                            onClick = {
                                showOptionsMenu = false
                                // TODO duplicate by insert a copy to database
                            })

                        DropdownMenuItem(
                            leadingIcon = { Icon(imageVector = Icons.Default.Delete, contentDescription = OPTIONS_DELETE)},
                            text = { Text(text = OPTIONS_DELETE, color = PurpleGrey40) },
                            onClick = {
                                showOptionsMenu = false
                                // TODO delete from database, show confirm dialog
                                showDeleteDialog = true
                            }
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "Remark")

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Level")
                Row {
                    // not sure add location or not
                    Text(text = "Location")
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = "Due Date")
                }
            }
        }

        if(showDeleteDialog){
            ConfirmDialog{ showDeleteDialog = it }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ItemHolderPreview() {
    Column(
        modifier = Modifier.padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemHolder()
    }
}