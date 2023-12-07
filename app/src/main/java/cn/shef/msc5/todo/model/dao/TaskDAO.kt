package cn.shef.msc5.todo.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cn.shef.msc5.todo.base.BaseDAO
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.enums.TaskStateEnum
import cn.shef.msc5.todo.utilities.Constants.Companion.TABLE_TASK
import kotlinx.coroutines.flow.Flow

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:15
 */
@Dao
interface TaskDAO : BaseDAO<Task> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Query("SELECT * from $TABLE_TASK WHERE id =:id and isDeleted = 0")
    fun findByPrimaryKey(id: Int): Task?

    @Query("SELECT * from $TABLE_TASK WHERE isDeleted = 0 and userId = :userId")
    fun getAllTasks(userId: Int): Flow<List<Task>> //List<Task>?

    @Query("SELECT * from $TABLE_TASK WHERE isDeleted = 0 and userId = :userId AND date(dueTime / 1000, 'unixepoch') = date(:selectedDate / 1000, 'unixepoch')")
    fun getAllTasksByDate(selectedDate: Long, userId: Int): Flow<List<Task>>

    @Query("SELECT * from $TABLE_TASK WHERE isDeleted = 0 and userId = :userId and state = :state")
    fun getTasksList(state: Int, userId: Int): Flow<List<Task>>

    @Query("SELECT count(*) from $TABLE_TASK WHERE isDeleted = 0 and userId = :userId")
    suspend fun getCount(userId: Int): Int

    override fun delete(task: Task) {
        task.isDeleted = 1
        update(task)
    }

    fun updateComplete(task: Task){
        task.state = TaskStateEnum.ISCOMPLETED.level
        update(task)
    }

    fun updateIncomplete(task: Task){
        task.state = TaskStateEnum.UNFINISHED.level
        update(task)
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task : Task)
}