package cn.shef.msc5.todo

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import cn.shef.msc5.todo.base.BaseActivity
import cn.shef.msc5.todo.utilities.Constants


/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 10:48
 *
 *      splash view to show some ads or app logo
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Using Material theme
            MaterialTheme {
                //Surface is a basic building block for displaying content and can be used to wrap other composable to provide a background color,
                //elevation, padding, and other layout properties.
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SplashScreen()
                    loadMainActivity()
                }
            }
        }
    }

    /**
     * jump to the main activity
     */
    private fun loadMainActivity() {
        //waiting 4 seconds in the new thread, using intent to jump to the main activity
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, Constants.DELAY_TIME)
    }
}

@Composable
fun SplashScreen() {
    //Column or Row, different layout. Sames like linear layout in the xml
    Column(
        //fill the entire screen
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Use Box to put elements on top of another.
        //https://developer.android.com/jetpack/compose/layouts/basics
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                //loading image resource
                painter = painterResource(R.mipmap.splash),
                contentDescription = null,
                //shape of the image
                contentScale = ContentScale.Crop,
                //fill the entire screen
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}

//preview this activity
@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}

