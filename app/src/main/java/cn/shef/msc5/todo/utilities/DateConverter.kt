package cn.shef.msc5.todo.utilities

import androidx.room.TypeConverter
import java.sql.Date
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

    fun formatDateYear(selectedDate: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.time = revertDate(selectedDate)

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
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