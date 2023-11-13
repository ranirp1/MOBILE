package cn.shef.msc5.todo.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import cn.shef.msc5.todo.R

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 05/11/2023 22:58
 */
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
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}
