package cn.shef.msc5.todo.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import java.sql.SQLException

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 02/11/2023 13:38
 */
interface BaseDAO<in T> {

    @Insert
    @Throws(SQLException::class)
    fun insert(vararg product: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLException::class)
    fun replaceInsert(vararg product: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg obj: T): Int

    @Delete
    fun delete(obj: T)
}