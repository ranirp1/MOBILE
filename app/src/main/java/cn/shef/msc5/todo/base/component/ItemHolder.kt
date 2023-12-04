package cn.shef.msc5.todo.base.component

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.shef.msc5.todo.activity.DetailActivity
import cn.shef.msc5.todo.base.component.dialog.ConfirmDialog
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.ui.theme.PurpleGrey40
import cn.shef.msc5.todo.utilities.Constants.Companion.OPTIONS_DELETE
import cn.shef.msc5.todo.utilities.Constants.Companion.OPTIONS_DUPLICATE
import cn.shef.msc5.todo.utilities.GeneralUtil

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ItemHolder(
    // TODO add the task variables
    context: Context,
    task: Task,
    mainViewModel: MainViewModel,
    colour: Int
) {
    var showOptionsMenu by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = colour)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        onClick = {
            // TODO go to edit page
            val intent = Intent(context, DetailActivity::class.java)
            GeneralUtil.startActivity3(context, intent)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.title, fontSize = 20.sp)
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

            Text(text = task.description)

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.priority.toString())
                Row {
                    // not sure add location or not
                    Text(text = "Location")
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = "Due on: ${task.dueTime}")
                }
            }
        }

        if(showDeleteDialog){
            ConfirmDialog(
                onClick = {
                    mainViewModel.delete(task)
                }
            ){ showDeleteDialog = it }
        }
    }
}
