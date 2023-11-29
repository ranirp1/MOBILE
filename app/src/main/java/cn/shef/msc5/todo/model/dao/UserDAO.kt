package cn.shef.msc5.todo.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cn.shef.msc5.todo.model.User
import cn.shef.msc5.todo.utilities.Constants

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 29/11/2023 11:49
 */
@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * from ${Constants.TABLE_TASK} WHERE id =:id and isDeleted = 0")
    fun findByPrimaryKey(id: Int): User?
}