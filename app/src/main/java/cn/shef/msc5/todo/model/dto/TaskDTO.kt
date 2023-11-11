package cn.shef.msc5.todo.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:10
 */
@Parcelize
data class TaskDTO(

    var id: Int,

    var title: String,

    var userId: Int,

    var description: String,

    var level: Int,

    var longitude: Float,

    var latitude: Float,

    var imageUrl: String,

    var gmtCreate: Date,

    var isDelete: Int,

    var remark: String


) : Parcelable{
    override fun toString(): String {
        return "TaskDTO(id=$id, title=$title, description=$description, level=$level, longitude=$longitude, latitude=$latitude, imageUrl=$imageUrl, gmtCreate=$gmtCreate, isDelete=$isDelete, remark='$remark)"
    }
}
