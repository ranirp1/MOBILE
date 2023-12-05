package cn.shef.msc5.todo.base.component

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.material.icons.filled.Done
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.shef.msc5.todo.activity.DetailActivity
import cn.shef.msc5.todo.base.component.dialog.ConfirmDialog
import cn.shef.msc5.todo.model.PriorityLevelEnum
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.ui.theme.PurpleGrey40
import cn.shef.msc5.todo.utilities.Constants.Companion.OPTIONS_DELETE
import cn.shef.msc5.todo.utilities.Constants.Companion.OPTIONS_DONE
import cn.shef.msc5.todo.utilities.Constants.Companion.OPTIONS_DUPLICATE
import cn.shef.msc5.todo.utilities.Constants.Companion.OPTIONS_UNDONE
import cn.shef.msc5.todo.utilities.DateConverter
import cn.shef.msc5.todo.utilities.GeneralUtil
import java.time.LocalDate
import cn.shef.msc5.todo.activity.ViewActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemHolder(
    task: Task,
    mainViewModel: MainViewModel
) {
    val dateConverter = DateConverter()
    val context = LocalContext.current
    var showOptionsMenu by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        enabled = !(task.isCompleted),
        onClick = {
            val intent = Intent(context, ViewActivity::class.java)
            intent.putExtra("taskId", task.id)
            GeneralUtil.startActivity2(context, intent)
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
                Text(text = task.title, fontSize = 20.sp)
                Box {
                    IconButton(
                        modifier = Modifier.size(20.dp),
                        onClick = {
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
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = OPTIONS_DONE
                                )
                            },
                            text = { Text(text = if(!task.isCompleted) OPTIONS_DONE else OPTIONS_UNDONE, color = PurpleGrey40) },
                            onClick = {
                                showOptionsMenu = false
                                mainViewModel.markAsDone(task)
                            }
                        )

                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ContentCopy,
                                    contentDescription = OPTIONS_DUPLICATE
                                )
                            },
                            text = { Text(text = OPTIONS_DUPLICATE, color = PurpleGrey40) },
                            onClick = {
                                showOptionsMenu = false
                                mainViewModel.duplicate(task,java.sql.Date.valueOf(LocalDate.now().toString()),
                                    java.sql.Date.valueOf(LocalDate.now().toString()), null)
                            }
                        )

                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = OPTIONS_DELETE
                                )
                            },
                            text = { Text(text = OPTIONS_DELETE, color = PurpleGrey40) },
                            onClick = {
                                showOptionsMenu = false
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = task.description)

            Spacer(modifier = Modifier.height(9.dp))

            when(PriorityLevelEnum.createFromInt(task.priority)){
                is PriorityLevelEnum.LOW -> Text(
                    text = PriorityLevelEnum.LOW.value,
                    fontStyle = FontStyle.Italic,
                    color = PriorityLevelEnum.LOW.color,
                    fontSize = 14.sp
                )
                is PriorityLevelEnum.MEDIUM -> Text(
                    text = PriorityLevelEnum.MEDIUM.value,
                    fontStyle = FontStyle.Italic,
                    color = PriorityLevelEnum.MEDIUM.color,
                    fontSize = 14.sp
                )
                is PriorityLevelEnum.HIGH -> Text(
                    text = PriorityLevelEnum.HIGH.value,
                    fontStyle = FontStyle.Italic,
                    color = PriorityLevelEnum.HIGH.color,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Due on: ${task.dueTime} at ${dateConverter.formatHourMinute(task.dueTime)}", fontSize = 14.sp)
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = "(" + task.latitude.toString() + ", " + task.longitude.toString() + ")",
                    fontSize = 14.sp
                )
            }
        }

        if (showDeleteDialog) {
            ConfirmDialog(
                onClick = {
                    mainViewModel.delete(task)
                }
            ){ showDeleteDialog = it }
        }
    }
}
