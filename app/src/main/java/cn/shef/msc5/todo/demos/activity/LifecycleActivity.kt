package cn.shef.msc5.todo.demos.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity


/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 11:56
 */
class LifecycleActivity : ComponentActivity() {

    private val TAG = "Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, " onCreate");
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, " onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, " onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, " onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, " onDestroy")
    }
}
