package cn.shef.msc5.todo

import android.app.Application
import android.content.Context


/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 18:15
 */
class AppApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private var instance: AppApplication? = null
        fun getApplicationContext() : Context {
            return instance!!.applicationContext
        }

    }

}