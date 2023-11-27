//package cn.shef.msc5.todo.demos.dao
//
//import android.os.Parcelable
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import kotlinx.android.parcel.Parcelize
//
///**
// * @author Zhecheng Zhao
// * @email zzhao84@sheffield.ac.uk
// * @date Created in 04/11/2023 19:10
// */
//@Parcelize
//@Entity(tableName = "h_todo")
//class ToDo(
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
//    var id: Int,
//
//    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
//    var name: String,
//
//    @ColumnInfo(name = "remarks", typeAffinity = ColumnInfo.TEXT)
//    var remarks: String
//
//
//) : Parcelable {
//    override fun toString(): String {
//        return "ToDo(id=$id, name='$name', remarks='$remarks')"
//    }
//}