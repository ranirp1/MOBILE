package uk.shef.msc5.todo

import android.app.Application
import android.content.Context
import uk.shef.msc5.todo.model.database.AppDatabase

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 18:15
 */
class AppApplication : Application() {

//    val database: AppDatabase by lazy { AppDatabase.INSTANCE }
    init {
        uk.shef.msc5.todo.AppApplication.Companion.instance = this
    }

    companion object {
        private var instance: uk.shef.msc5.todo.AppApplication? = null
        fun getApplicationContext() : Context {
            return uk.shef.msc5.todo.AppApplication.Companion.instance!!.applicationContext
        }

    }

}