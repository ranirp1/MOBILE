package cn.shef.msc5.todo.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.CaptureImageActivity
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.CheckboxListTextFieldExample
import cn.shef.msc5.todo.base.component.Chips
import cn.shef.msc5.todo.base.component.DatePicker
import cn.shef.msc5.todo.base.component.bottombar.BottomActionBar
import cn.shef.msc5.todo.base.component.dialog.TimePickerDialog
import cn.shef.msc5.todo.model.dto.SubTask
import cn.shef.msc5.todo.model.getPriorityValues
import cn.shef.msc5.todo.model.getTemplateStr
import cn.shef.msc5.todo.model.getTemplateTextStr
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.utilities.DateConverter
import cn.shef.msc5.todo.utilities.GeneralUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.sql.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 13:28
 */
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope: CoroutineScope = rememberCoroutineScope()
    val dateConverter = DateConverter()

    var showCalender by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(mainViewModel.date) }
    val state = rememberTimePickerState()

    val priorityLevels = getPriorityValues()
    var prior by remember { mutableIntStateOf(2) }

    val templates = getTemplateStr()
    val templateDesc = getTemplateTextStr()
    var selectedTemplate by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var subTasks by remember { mutableStateOf(listOf(SubTask("Enter subtask", false))) }

    if (selectedTemplate.isNotBlank()) {
        val templateIndex = templates.indexOf(selectedTemplate)
        if (templateIndex != -1 && templateIndex < templateDesc.size) {
            text = templateDesc[templateIndex]
            title = templates[templateIndex]
        }
    }

    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = true,
        showFirstIcon = false,
        showSecondIcon = false,
        title = stringResource(R.string.todo_new_task),
        hostState = snackbarHostState,
        bottomBar = {
            BottomActionBar(modifier = Modifier.height(70.dp),
                title = "Save",
                onCamera = {
                    //TODO add the bottomsheet here

                    val intent = Intent(context, CaptureImageActivity::class.java)
                    GeneralUtil.startActivity2(context, intent)
                },
                onLocation = {

                },
//                onSubTask = {
//                    scope.launch {
//
//                    }
//                },
                onCalender = {
                    scope.launch {
                        showCalender = !showCalender
                    }
                },
                onReminder = {
                    scope.launch {
                        showTimePicker = !showTimePicker
                    }
                },
                addClick = {
                    if(title.isEmpty()){
                        scope.launch{
                            snackbarHostState.showSnackbar("Please fill in a title!")
                        }
                    }else{
                        mainViewModel.addTask(
                            title, text, prior, 1.11F, 1.11F,
                            "imageUrl", Date.valueOf(LocalDate.now().toString()),
                            Date.valueOf(LocalDate.now().toString()), date,
                            0, false, subTasks, null
                        )
                        GeneralUtil.finishActivity2(context)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Chips("", "Templates: ", templates) {
                selectedTemplate = it
            }

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

            Chips(priorityLevels[1], "Priority: ", priorityLevels) {
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

            if (showCalender) {
                DatePicker(
                    onDateSelected = { date = dateConverter.updateDateWithTime(it, state) },
                    onDismiss = { showCalender = false }
                )
            }

            if(showTimePicker){
                TimePickerDialog(
                    onCancel = { showTimePicker = false },
                    onConfirm = {
                        date = dateConverter.updateDateWithTime(date, state)
                        showTimePicker = false
                    }
                ) {
                    TimePicker(state = state)
                }
            }
        }
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailScreen() {
//    MainScreen(LocalContext.current)
}
