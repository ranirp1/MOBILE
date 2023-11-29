package cn.shef.msc5.todo.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cn.shef.msc5.todo.base.BaseDAO
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.utilities.Constants.Companion.TABLE_TASK
import kotlinx.coroutines.flow.Flow
import java.util.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:15
 */
@Dao
interface TaskDAO : BaseDAO<Task> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Query("SELECT * from $TABLE_TASK WHERE id =:id and isDeleted = 0")
    fun findByPrimaryKey(id: Int): Task?

    @Query("SELECT * from $TABLE_TASK WHERE isDeleted = 0")
    fun getAllTasks(): Flow<List<Task>> //List<Task>?

    @Query("SELECT * from $TABLE_TASK WHERE isDeleted = 0 AND strftime('%d%m%Y', dueTime) = :selectedDate")
    fun getAllTasksByDate(selectedDate: String): Flow<List<Task>>

    @Query("SELECT count(*) from $TABLE_TASK WHERE isDeleted = 0")
    suspend fun getCount(): Int

    override fun delete(task: Task) {
        task.isDeleted = 1
        insert(task)
    }
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task : Task)
}