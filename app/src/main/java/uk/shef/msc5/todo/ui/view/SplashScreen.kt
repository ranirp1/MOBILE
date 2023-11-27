package uk.shef.msc5.todo.ui.view

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.shef.msc5.todo.R
import kotlinx.coroutines.delay

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 05/11/2023 22:58
 */
@ExperimentalAnimationApi
@Composable
fun SplashScreen() {

    val imageVisible = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true){
        for (i in 0..10){
            delay(1500)
            imageVisible.value = !imageVisible.value
        }
    }

//    Column or Row, different layout. Sames like linear layout in the xml
    Column(
        //fill the entire screen
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Use Box to put elements on top of another.
        //https://developer.android.com/jetpack/compose/layouts/basics
        Box(modifier = Modifier
            .background(color = colorResource(id = R.color.white))
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {
            this@Column.AnimatedVisibility(visible = imageVisible.value, modifier = Modifier.align(Alignment.Center)) {
                Image(
                    //loading image resource
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    //shape of the image
                    contentScale = ContentScale.Crop,
                    //fill the entire screen
                    modifier = Modifier.size(240.dp)
                )
            }
        }

    }
}

//preview this activity
@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@ExperimentalAnimationApi
fun PreviewSplashScreen() {
    SplashScreen()
}
