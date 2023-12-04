package cn.shef.msc5.todo.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cn.shef.msc5.todo.ui.theme.AppTheme
import cn.shef.msc5.todo.ui.view.SplashScreen
import cn.shef.msc5.todo.utilities.Constants


/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 10:48
 *
 *      splash view to show some ads or app logo
 */
class SplashActivity : ComponentActivity() {


    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //Using Material theme
            AppTheme {
                //Surface is a basic building block for displaying content and can be used to wrap other composable to provide a background color,
                //elevation, padding, and other layout properties.
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SplashScreen()
                }
            }
        }
        loadMainActivity()
    }

    /**
     * jump to the main activity
     */
    private fun loadMainActivity() {
        //waiting 4 seconds in the main thread, using intent to jump to the main activity
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, Constants.DELAY_TIME)
    }
}


