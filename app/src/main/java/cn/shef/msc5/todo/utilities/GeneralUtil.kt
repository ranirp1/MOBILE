package cn.shef.msc5.todo.utilities

import android.content.Context
import android.content.Intent
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.base.BaseActivity

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 23:24
 */
class GeneralUtil {
    companion object {

        fun startActivity(context: Context, intent: Intent) {
            (context as BaseActivity).startActivity(intent)
            context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out)
        }

        fun startActivity2(context: Context, intent: Intent) {
            (context as BaseActivity).startActivity(intent)
            context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        }

        fun finishActivity(context: Context) {
            (context as BaseActivity).finish()
            context.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out)
        }

        fun finishActivity2(context: Context) {
            (context as BaseActivity).finish()
//            context.overridePendingTransition(R.anim.slide_bottom_in, R.anim.slide_top_out)
//            context.overridePendingTransition(R.anim.slide_bottom_in, 0)
        }

    }
}