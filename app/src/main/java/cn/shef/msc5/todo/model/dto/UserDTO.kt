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
data class UserDTO(

    var id: Int,

    var name: String,

    var isDeleted: Int,

) : Parcelable{
    override fun toString(): String {
        return "TaskDTO(id=$id, name=$name, isDeleted=$isDeleted)"
    }
}
