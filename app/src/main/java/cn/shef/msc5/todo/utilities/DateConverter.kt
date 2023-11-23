package cn.shef.msc5.todo.utilities

import androidx.room.TypeConverter
import java.sql.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 10/11/2023 01:24
 */
class DateConverter {
    @TypeConverter
    fun revertDate(value: Long): Date {
        return Date(value);
    }

    @TypeConverter
    fun converterDate(value: Date): Long {
        return value.time;
    }
}