package cn.shef.msc5.todo.base.component

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.demos.ui.datepickers.DatePicker
import cn.shef.msc5.todo.utilities.DateConverter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.sql.Date
import java.util.Locale

/**
 * @author Cheng Man Li
 * @email cmli1@sheffield.ac.uk
 * @date Created on 16/11/2023 23:58
 */

@Composable
fun DatePickerBar(
    onDateSelected: (Date) -> Unit
) {
    val dateConverter = DateConverter()
    var date by remember { mutableStateOf(Date(System.currentTimeMillis())) }
    var showDatePicker by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            date = dateConverter.getPrevDay(date)
            onDateSelected(date)
        }) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "Left arrow"
            )
        }

        TextButton(
            modifier = Modifier.weight(1f),
            onClick = {
                showDatePicker = true
            }
        ) {
            Text(text = dateConverter.formatDateMonth(date))
        }

        IconButton(onClick = {
            date = dateConverter.getNextDay(date)
            onDateSelected(date)
        }) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Right arrow"
            )
        }
        if (showDatePicker) {
            DatePicker(
                onDateSelected = { date = it },
                onDismiss = {
                    showDatePicker = false
                    onDateSelected(date)
                }
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
    val datePickerState = rememberDatePickerState(Date(System.currentTimeMillis()).time)

    // convert the selected date into a date object
    val selectedDate = datePickerState.selectedDateMillis?.let { Date(it) } ?: Date(System.currentTimeMillis())

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
