package cn.shef.msc5.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 10:48
 */
@Entity(tableName = "h_user")
class User {

    constructor(id: Int, username: String, password: String, email: String){
        this.id = id;
        this.username = username
        this.password = password
        this.email = email
    }

    /**
     * user id
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int = 0

    /**
     * user name
     */
    @ColumnInfo(name = "username", typeAffinity = ColumnInfo.TEXT)
    var username: String

    /**
     * user password
     */
    @ColumnInfo(name = "password", typeAffinity = ColumnInfo.TEXT)
    var password: String

    /**
     * user email
     */
    @ColumnInfo(name = "email", typeAffinity = ColumnInfo.TEXT)
    var email: String

    @Ignore
    lateinit var remark: String

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email', remark='$remark')"
    }
}