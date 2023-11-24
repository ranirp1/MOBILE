package uk.shef.msc5.todo.utilities

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 23:24
 */
class DateUtil {
    companion object {
        fun formatDate(selectedDate: Date): String {
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
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
    }
}