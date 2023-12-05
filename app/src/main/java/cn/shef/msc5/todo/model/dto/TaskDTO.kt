package cn.shef.msc5.todo.model.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize
import java.sql.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:10
 */

@Parcelize
data class SubTask(
    var text: String,
    var isChecked: Boolean = false
) : Parcelable

@Parcelize
data class TaskDTO(

    var id: Int,

    var title: String,

    var userId: Int,

    var description: String,

    var priority: Int,

    var longitude: Float,

    var latitude: Float,

    var imageUrl: String,

    var dueTime: Date,

    var parentId: Int,

    var gmtCreated: Date,

    var gmtModified: Date,

    var isDeleted: Int,

    var state: Int,

    var subTasks: List<SubTask>

) : Parcelable{
    override fun toString(): String {
        return "TaskDTO(id=$id, title=$title, description=$description, priority=$priority, longitude=$longitude, latitude=$latitude, imageUrl=$imageUrl, dueTime=$dueTime, parentId=$parentId, gmtCreated=$gmtCreated, gmtModified=$gmtModified, isDeleted=$isDeleted, state=$state, subTasks=$subTasks)"
    }
}
