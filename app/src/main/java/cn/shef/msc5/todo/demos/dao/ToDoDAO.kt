package cn.shef.msc5.todo.demos.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:15
 */
@Dao
interface ToDoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(toDo: ToDo)

    @Query("select * from h_ToDo where id =:id")
    suspend fun findByPrimaryKey(id: String): ToDo?

    @Query("select * from h_ToDo")
    suspend fun getAllStudent(): List<ToDo>?

    @Delete
    suspend fun delete(toDo : ToDo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(toDo : ToDo)
}