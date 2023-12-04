package cn.shef.msc5.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import cn.shef.msc5.todo.utilities.Constants.Companion.TABLE_TASK
import cn.shef.msc5.todo.utilities.DateConverter
import java.sql.Date
import java.time.OffsetDateTime

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 29/11/2023 11:48
 */
@TypeConverters(DateConverter::class)
@Entity(tableName = TABLE_TASK)
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int,

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name: String,

    @ColumnInfo(name = "isDeleted", typeAffinity = ColumnInfo.INTEGER)
    var isDeleted: Int,


){
    override fun toString(): String {
        return "Task(id=$id, name=$name, isDeleted=$isDeleted)"
    }
}
