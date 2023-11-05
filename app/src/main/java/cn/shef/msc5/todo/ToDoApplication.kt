package cn.shef.msc5.todo

import android.app.Application
import android.content.Context

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 18:15
 */
class ToDoApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: ToDoApplication? = null
        fun getApplicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}