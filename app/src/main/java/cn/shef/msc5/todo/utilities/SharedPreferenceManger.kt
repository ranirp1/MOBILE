package cn.shef.msc5.todo.utilities

import android.content.Context

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 07/12/2023 12:16
 */

class SharedPreferenceManger(context: Context) {
    private val preference = context.getSharedPreferences(
        context.packageName,
        Context.MODE_PRIVATE
    )
    private val editor = preference.edit()

    private val islogin = "islogin"
    private val id = "userId"
    private val name = "userName"

    var userId
        get() = preference.getInt(id, 1)
        set(value) {
            editor.putInt(id, value)
            editor.commit()
        }

    var userName
        get() = preference.getString(name, "Admin")
        set(value) {
            editor.putString(name, value)
            editor.commit()
        }

    var isLogin
        get() = preference.getBoolean(islogin, false)
        set(value) {
            editor.putBoolean(islogin, true)
            editor.commit()
        }

    fun getStringValue(key: String): String? {
        return preference.getString(key, "")
    }

    fun getIntegerValue(key: String): Int {
        return preference.getInt(key, 0)
    }

    fun getBooleanValue(key: String, default: Boolean = false): Boolean {
        return preference.getBoolean(key, default)
    }
}