package cn.shef.msc5.todo.ui.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.CheckboxListTextFieldExample
import cn.shef.msc5.todo.base.component.Chips
import cn.shef.msc5.todo.base.component.bottombar.BottomConfirmBar
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.getPriorityValues
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.utilities.DateConverter
import kotlinx.coroutines.CoroutineScope

/**
 * @author Raghav Chhabra
 * @email rchhabra1@sheffield.ac.uk
 */
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ViewScreen(
    task: Task,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope: CoroutineScope = rememberCoroutineScope()
    val dateConverter = DateConverter()

//    var showCalender by remember { mutableStateOf(false) }
//    var showTimePicker by remember { mutableStateOf(false) }
//    var date by remember { mutableStateOf(mainViewModel.date) }
//    val state = rememberTimePickerState()

    val priorityLevels = getPriorityValues()
    var prior by remember { mutableIntStateOf(task.priority) }

//    val templates = getTemplateStr()
//    val templateDesc = getTemplateTextStr()
//    var selectedTemplate by remember { mutableStateOf("") }
//    var title by remember { mutableStateOf(task.title) }
//    var text by remember { mutableStateOf(task.description) }
//    var subTasks by remember { mutableStateOf(task.subTasks) }

    var title = task.title
    var text = task.description
    var subTasks = task.subTasks
    val date = task.dueTime

//    if (selectedTemplate.isNotBlank()) {
//        val templateIndex = templates.indexOf(selectedTemplate)
//        if (templateIndex != -1 && templateIndex < templateDesc.size) {
//            text = templateDesc[templateIndex]
//            title = templates[templateIndex]
//        }
//    }

    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = true,
        showFirstIcon = false,
        showSecondIcon = false,
        title = "",
        hostState = snackbarHostState,
        bottomBar = {
            BottomConfirmBar(
                title = "Edit",
                addClick = {

                }
            )
        }
//            BottomActionBar(modifier = Modifier.height(70.dp),
//                title = "Update",
//                onCamera = {
//                    val intent = Intent(context, CaptureImageActivity::class.java)
//                    GeneralUtil.startActivity2(context, intent)
//                },
//                onLocation = {
//
//                },
//                onSubTask = {
//                    scope.launch {
//
//                    }
//                },
//                onCalender = {
//                    scope.launch {
//                        showCalender = !showCalender
//                    }
//                },
//                onReminder = {
//                    scope.launch {
//                        showTimePicker = !showTimePicker
//                    }
//                },
//                addClick = {
//                    mainViewModel.addTask(
//                        title, text, prior, 1.11F, 1.11F,
//                        "imageUrl", task.gmtCreated,
//                        Date.valueOf(LocalDate.now().toString()), date,
//                        0, false, subTasks, null
//                    )
//                    GeneralUtil.finishActivity2(context)
//                }
//            )
//        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .heightIn(80.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp),
                value = title,
                singleLine = true,
                label = { Text(text = "Title") },
                onValueChange = { title = it }
            )

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .heightIn(135.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp), value = text,
                maxLines = 4,
                minLines = 1,
                label = { Text(text = "Description") },
                onValueChange = { text = it }
            )

            Chips(priorityLevels[task.priority - 1], "Priority: ", priorityLevels) {
                prior = priorityLevels.indexOf(it) + 1
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                text = "Location: "
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = "Due Date: $date at ${dateConverter.formatHourMinute(date)}",
            )

            CheckboxListTextFieldExample(subTasks){
                subTasks = it
            }

//            if (showCalender) {
//                DatePicker(
//                    onDateSelected = { date = dateConverter.updateDateWithTime(it, state) },
//                    onDismiss = { showCalender = false }
//                )
//            }
//
//            if(showTimePicker){
//                TimePickerDialog(
//                    onCancel = { showTimePicker = false },
//                    onConfirm = {
//                        date = dateConverter.updateDateWithTime(date, state)
//                        showTimePicker = false
//                    }
//                ) {
//                    TimePicker(state = state)
//                }
//            }
        }
    }
}

