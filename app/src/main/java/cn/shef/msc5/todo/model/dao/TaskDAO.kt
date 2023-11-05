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

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:15
 */
@Dao
interface TaskDAO : BaseDAO<Task> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Query("select * from $TABLE_TASK where id =:id")
    fun findByPrimaryKey(id: Int): Task?

    @Query("select * from $TABLE_TASK")
    fun getAllTasks(): Flow<List<Task>> //List<Task>?

    @Query("select count(*) from $TABLE_TASK")
    suspend fun getCount(): Int

    override fun delete(task: Task) {
        TODO("Not yet implemented")
    }
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task : Task)
}