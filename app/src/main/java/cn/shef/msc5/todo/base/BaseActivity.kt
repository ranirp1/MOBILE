package cn.shef.msc5.todo.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 10:48
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(this.localClassName, "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.v(this.localClassName, "onStart")
    }

    override fun finish() {
        super.finish()
        Log.v(this.localClassName, "finish")
    }
}
