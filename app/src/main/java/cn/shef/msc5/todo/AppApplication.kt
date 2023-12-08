package cn.shef.msc5.todo

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import cn.shef.msc5.todo.model.User
import cn.shef.msc5.todo.utilities.DataStoreFactory


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
        DataStoreFactory.init(this)
    }

    companion object {
        private var instance: AppApplication? = null
        fun getApplicationContext() : Context {
            return instance!!.applicationContext
        }

    }

}