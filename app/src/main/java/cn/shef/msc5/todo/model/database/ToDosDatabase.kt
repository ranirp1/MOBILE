//package cn.shef.msc5.todo.model.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import cn.shef.msc5.todo.model.User
//import cn.shef.msc5.todo.model.dao.UserDAO
//
///**
// * @author Zhecheng Zhao
// * @email zzhao84@sheffield.ac.uk
// * @date Created in 04/11/2023 17:27
// */
//@Database(entities = [User::class], version = 1, exportSchema = false)
//abstract class ToDosDatabase : RoomDatabase() {
//
//    abstract fun userDAO(): UserDAO
//
//    companion object {
//
//        private lateinit var instance: ToDosDatabase
//
//        fun inst(context: Context): ToDosDatabase {
//            if (!::instance.isInitialized) {
//                synchronized(ToDosDatabase::class) {
//                    // create database
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        ToDosDatabase::class.java,
//                        "todoList.db")
//                        .allowMainThreadQueries()
//                        .build()
//                }
//            }
//            return instance;
//        }
//    }
//}