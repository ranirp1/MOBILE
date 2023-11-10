package cn.shef.msc5.todo.model.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.shef.msc5.todo.utilities.Constants.Companion.TABLE_TASK
import kotlinx.android.parcel.Parcelize
import java.sql.Date

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

    @ColumnInfo(name = "title", typeAffinity = ColumnInfo.TEXT)
    var title: String,

    @ColumnInfo(name = "description", typeAffinity = ColumnInfo.TEXT)
    var description: String,

    @ColumnInfo(name = "level", typeAffinity = ColumnInfo.INTEGER)
    var level: Int,

    @ColumnInfo(name = "longitude", typeAffinity = ColumnInfo.REAL)
    var longitude: Float,

    @ColumnInfo(name = "latitude", typeAffinity = ColumnInfo.REAL)
    var latitude: Float,

    @ColumnInfo(name = "imageUrl", typeAffinity = ColumnInfo.TEXT)
    var imageUrl: String,

    @ColumnInfo(name = "gmtCreate")
    var gmtCreate: Date,

    @ColumnInfo(name = "remark", typeAffinity = ColumnInfo.TEXT)
    var remark: String


) : Parcelable{
    override fun toString(): String {
        return "Task(id=$id, title=$title, description=$description, level=$level, longitude=$longitude, latitude=$latitude, imageUrl=$imageUrl, gmtCreate=$gmtCreate, remark='$remark)"
    }
}
