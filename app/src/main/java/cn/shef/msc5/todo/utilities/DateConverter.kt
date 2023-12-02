package cn.shef.msc5.todo.utilities

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.room.TypeConverter
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 10/11/2023 01:24
 */
class DateConverter {
    @TypeConverter
    fun revertDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun converterDate(value: Date): Long {
        return value.time
    }

    fun formatDateMonth(selectedDate: java.util.Date): String {
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        return dateFormat.format(selectedDate)
    }

    fun formatHourMinute(date: Date): String {
        val timeStr = java.util.Date(date.time)
        val format = SimpleDateFormat("HH:mm")
        return format.format(timeStr)
    }

    fun formatDateYear(selectedDate: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.time = revertDate(selectedDate)

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun updateDateWithTime(originalDate: Date, state: TimePickerState): Date {
        val timestamp = Timestamp(originalDate.time)

        // Get the time from state
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp.time
        calendar.set(Calendar.HOUR_OF_DAY, state.hour)
        calendar.set(Calendar.MINUTE, state.minute)

        timestamp.time = calendar.timeInMillis

        return Date(timestamp.time)
    }

    fun getPrevDay(selectedDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = selectedDate
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return Date(calendar.timeInMillis)
    }

    fun getNextDay(selectedDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = selectedDate
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return Date(calendar.timeInMillis)
    }
}