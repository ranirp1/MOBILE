package cn.shef.msc5.todo.utilities

import android.app.Activity
import android.content.Context

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 23:24
 */
class GeneralUtil {
    companion object {
        fun finishActivity(context: Context) {
            (context as Activity).finish()
        }

    }
}