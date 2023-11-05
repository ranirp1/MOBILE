package cn.shef.msc5.todo.model.database


import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cn.shef.msc5.todo.ToDoApplication
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.dao.TaskDAO
import cn.shef.msc5.todo.utilities.Constants.Companion.DATABASE_TASK

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:18
 */
@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class ToDosDatabase : RoomDatabase() {
    companion object {
        var dataBase: ToDosDatabase
        val TAG = ToDosDatabase::class.java.simpleName
        init {
            dataBase = Room.databaseBuilder(ToDoApplication.getApplicationContext(), ToDosDatabase::class.java, DATABASE_TASK)
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d(TAG, "onCreate: $DATABASE_TASK")
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }

    }

    abstract fun getTaskDAO(): TaskDAO
}
