package cn.shef.msc5.todo.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cn.shef.msc5.todo.base.BaseActivity
import cn.shef.msc5.todo.ui.theme.AppTheme
import cn.shef.msc5.todo.ui.view.LoginScreen

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 07/12/2023 11:59
 */
class LoginActivity : BaseActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //TODO request permissions
        Log.d(TAG, "onStart: ")
    }

}
