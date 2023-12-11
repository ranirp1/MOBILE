package cn.shef.msc5.todo.utilities

import android.content.Context
import android.content.Intent
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.base.BaseActivity

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 23:24
 *
 *      start activity animation
 */
class GeneralUtil {
    companion object {

        fun startActivitySlideIn(context: Context, intent: Intent) {
            (context as BaseActivity).startActivity(intent)
            context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out)
        }

        fun startActivitySlideUp(context: Context, intent: Intent) {
            (context as BaseActivity).startActivity(intent)
            context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_no_animation)
        }

        fun finishActivitySlideOut(context: Context) {
            (context as BaseActivity).finish()
            context.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out)
        }

        fun finishActivitySlideDown(context: Context) {
            (context as BaseActivity).finish()
            context.overridePendingTransition(R.anim.slide_no_animation, R.anim.slide_out_up)
        }

    }
}