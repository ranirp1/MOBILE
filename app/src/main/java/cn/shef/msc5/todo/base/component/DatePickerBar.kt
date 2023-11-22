package cn.shef.msc5.todo.base.component

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.demos.ui.datepickers.DatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * @author Cheng Man Li
 * @email cmli1@sheffield.ac.uk
 * @date Created on 16/11/2023 23:58
 */

@Preview
@Composable
fun DatePickerBar() {
    var date by remember { mutableStateOf(Date()) }
    var showDatePicker by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // left arrow
        IconButton(onClick = {
            date = getPrevDay(date)
        }) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "Left arrow"
            )
        }

        // date in middle
        TextButton(
            onClick = { showDatePicker = true }
        ) {
            Text(text = formatDate(date))
        }

        // right arrow
        IconButton(onClick = {
            date = getNextDay(date)
        }) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Right arrow"
            )
        }
        if (showDatePicker) {
            DatePicker(
                onDateSelected = { date = it },
                onDismiss = { showDatePicker = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    onDateSelected: (Date) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(Date().time)

    // convert the selected date into a date object
    val selectedDate = datePickerState.selectedDateMillis?.let { Date(it) } ?: Date()

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            }) {
                Text("Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    ) {
        androidx.compose.material3.DatePicker(state = datePickerState)
    }
}

fun formatDate(selectedDate: Date): String {
    val dateFormat = SimpleDateFormat("dd-MMM", Locale.getDefault())
    return dateFormat.format(selectedDate)
}

fun getPrevDay(selectedDate: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = selectedDate
    calendar.add(Calendar.DAY_OF_YEAR, -1)
    return calendar.time
}

fun getNextDay(selectedDate: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = selectedDate
    calendar.add(Calendar.DAY_OF_YEAR, 1)
    return calendar.time
}