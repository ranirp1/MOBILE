package uk.shef.msc5.todo.model.database

import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import uk.shef.msc5.todo.AppApplication
import uk.shef.msc5.todo.model.Task
import uk.shef.msc5.todo.model.dao.TaskDAO
import uk.shef.msc5.todo.utilities.Constants.Companion.DATABASE_TASK

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 19:18
 */
@Database(entities = [Task::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        var INSTANCE: AppDatabase
        val TAG = AppDatabase::class.java.simpleName
        init {
            INSTANCE = Room.databaseBuilder(
                uk.shef.msc5.todo.AppApplication.getApplicationContext(),
                AppDatabase::class.java, DATABASE_TASK)
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
