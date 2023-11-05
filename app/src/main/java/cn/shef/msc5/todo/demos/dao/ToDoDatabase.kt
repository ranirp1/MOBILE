//package cn.shef.msc5.todo.demos.dao
//
//import android.util.Log
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.sqlite.db.SupportSQLiteDatabase
//import cn.shef.msc5.todo.ToDoApplication
//import cn.shef.msc5.todo.model.Task
//
///**
// * @author Zhecheng Zhao
// * @email zzhao84@sheffield.ac.uk
// * @date Created in 04/11/2023 19:18
// */
//@Database(entities = [ToDo::class], version = 1, exportSchema = false)
//abstract class ToDoDatabase : RoomDatabase() {
//
//    companion object {
//        var dataBase: ToDoDatabase
//        val TAG = ToDoDatabase::class.java.simpleName
//        init {
//            dataBase = Room.databaseBuilder(ToDoApplication.getApplicationContext(), ToDoDatabase::class.java, "db_todo")
//                .allowMainThreadQueries()
//                .addCallback(object : Callback() {
//                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        super.onCreate(db)
//                        Log.d(TAG, "onCreate: db_todo")
//                    }
//                })
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//
//    }
//
//    abstract fun getToDoDAO(): ToDoDAO
//}