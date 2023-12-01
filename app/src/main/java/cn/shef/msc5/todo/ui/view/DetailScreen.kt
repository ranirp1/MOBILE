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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.CaptureImageActivity
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.base.component.DatePicker
import cn.shef.msc5.todo.base.component.bottombar.BottomActionBar
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.utilities.GeneralUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 13:28
 */
@ExperimentalAnimationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(mainViewModel: MainViewModel) {
    var text by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope: CoroutineScope = rememberCoroutineScope()
    var showPriorMenu by remember { mutableStateOf(false) }
    var showCalender by remember { mutableStateOf(false) }
    var prior by remember { mutableIntStateOf(2) }
    var date by remember { mutableStateOf(Date()) }

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
                onSubTask = {
                    scope.launch {

                    }
                },
                onCalender = {
                    scope.launch {
                        showCalender = !showCalender
                    }
                },
                onPriority={
                    scope.launch {
                        showPriorMenu= !showPriorMenu
                    }
                },
                addClick = {
                    mainViewModel.addTask(title, text, prior, 1.11F, 1.11F,
                        "imageUrl", java.sql.Date.valueOf(LocalDate.now().toString()),
                        java.sql.Date.valueOf(LocalDate.now().toString()), date.toSqlDate(),
                        0,"remark", null)
                    GeneralUtil.finishActivity2(context)
                }
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            //.heightIn(80.dp)
            ,verticalArrangement = Arrangement.Top
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
                .heightIn(220.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp), value = text,
                maxLines = 10,
                minLines = 1,
                label = { Text(text = "Description") },
                onValueChange = { text = it }
            )

            Text(modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp)
                .padding(10.dp), text = "Selected Task Location is "
            )

            Text(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(10.dp)
                    .padding(10.dp),
                text = "Due Date: $date",
            )

            Text(modifier = Modifier
                .fillMaxWidth()
                .heightIn(10.dp)
                .padding(10.dp), text = "Due time:"
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                , verticalArrangement = Arrangement.Bottom
                , horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(showPriorMenu){
                    DropdownMenu(expanded = true, onDismissRequest = { showPriorMenu = false }) {
                        DropdownMenuItem(onClick = {
                            prior = 1
                            showPriorMenu = false
                        }) {
                            Text(text = "High priority")
                        }

                        DropdownMenuItem(onClick = {
                            prior = 2
                            showPriorMenu = false
                        }) {
                            Text(text = "Medium priority")
                        }

                        DropdownMenuItem(onClick = {
                            prior=3
                            showPriorMenu = false
                        }) {
                            Text(text = "Low priority")
                        }
                    }
                }
                if (showCalender) {
                    DatePicker(
                        onDateSelected = { date = it },
                        onDismiss = {
                            showCalender = false
                        }
                    )
                }
            }
        }
    }
}

fun Date.toSqlDate(): java.sql.Date {
    return java.sql.Date(this.time)
}
@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDetailScreen() {
//    MainScreen(LocalContext.current)
}
