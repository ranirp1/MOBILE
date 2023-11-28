package cn.shef.msc5.todo.base.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.AddAlert
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Camera
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R
import kotlinx.coroutines.CoroutineScope

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 14/11/2023 04:24
 */
@Composable
fun BottomActionBar(
    modifier: Modifier = Modifier,
    title: String,
    onLocation: () -> Unit = {},
    onCamera: () -> Unit = {},
    onCalender:() -> Unit={},
    onPriority:()-> Unit={},
    onSubTask: () -> Unit = {},
    addClick: () -> Unit = {},
) {
    val scope: CoroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionIcons(
                onLocation = onLocation,
                onCamera = onCamera,
                onCalender=onCalender,
                onPriority=onPriority,
                onSubTask = onSubTask
            )
            Spacer(modifier = modifier.width(16.dp))
            Row(modifier = modifier.fillMaxWidth(), Arrangement.End) {
                androidx.compose.material.Button(
                    onClick = addClick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    androidx.compose.material.Icon(
                        Icons.Filled.Check,
//                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = title,
                        tint = Color.White
                    )
                    Spacer(modifier = modifier.width(12.dp))
                    androidx.compose.material.Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ActionIcons(
    onLocation: () -> Unit,
    onCamera: () -> Unit,
    onCalender: () -> Unit,
    onPriority: () -> Unit,
    onSubTask: () -> Unit) {
    Row(modifier = Modifier.wrapContentWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        IconButton(onClick = { onLocation.invoke() }) {
            Icon(
                Icons.Rounded.LocationOn,
                contentDescription = stringResource(R.string.todo_new_task),
                tint = Color.White
            )
        }

        IconButton(onClick = { onCamera.invoke() }) {
            Icon(
                Icons.Rounded.Camera,
                contentDescription = stringResource(R.string.todo_new_task),
                tint = Color.White
            )
        }

        IconButton(onClick = { onPriority.invoke() }) {
            Icon(
                Icons.Rounded.AddAlert,
                contentDescription = stringResource(R.string.todo_new_task),
                tint = Color.White
            )
        }

        IconButton(onClick = { onCalender.invoke() }) {
            Icon(
                Icons.Rounded.CalendarMonth,
                contentDescription = stringResource(R.string.todo_new_task),
                tint = Color.White
            )
        }

        IconButton(onClick = { onSubTask.invoke() }) {
            Icon(
                Icons.Rounded.AddTask,
                contentDescription = stringResource(R.string.todo_new_task),
                tint = Color.White
            )
        }
    }
}