package cn.shef.msc5.todo.model.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.shef.msc5.todo.utilities.Constants.Companion.TABLE_TASK
import kotlinx.android.parcel.Parcelize

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:10
 */
@Parcelize
@Entity(tableName = TABLE_TASK)
data class TaskDTO(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int,

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name: String,

    @ColumnInfo(name = "level", typeAffinity = ColumnInfo.INTEGER)
    var level: Int,

    @ColumnInfo(name = "remark", typeAffinity = ColumnInfo.TEXT)
    var remark: String


) : Parcelable{
    override fun toString(): String {
        return "Task(id=$id, name='$name', level=$level, remark='$remark')"
    }
}
