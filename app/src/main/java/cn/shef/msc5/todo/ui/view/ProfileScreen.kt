package cn.shef.msc5.todo.ui.view

import android.content.Context
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Divider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.LocationActivity
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.utilities.AppInfoUtil
import cn.shef.msc5.todo.utilities.GeneralUtil

@ExperimentalAnimationApi
@Composable
fun ProfileScreen(context: Context,
                  mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = false,
        secondIcon = Icons.Default.Sort,
        showFirstIcon = false,
        showSecondIcon = true,
        title = stringResource(R.string.todo_profile),
        hostState = snackbarHostState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.white)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileImage("UserName")
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        horizontal = 15.dp
                    )
            ) {
                HeaderText(text = stringResource(R.string.profile_account))
//                switchTheme()
                ProfileText(stringResource(R.string.profile_location),
                    onClick = {
                        val intent = Intent(context, LocationActivity::class.java)
                        GeneralUtil.startActivity(context, intent)
                    })
                HeaderText(text = stringResource(R.string.profile_about))
                ProfileText(stringResource(R.string.profile_version) + "        " +
                        AppInfoUtil.getAppVersionName(context))
                ProfileText(stringResource(R.string.profile_policy))
            }
        }
    }
}

@Composable
fun HeaderText(text: String) {
    Text(
        modifier = Modifier
            .padding(end = 32.dp, top = 20.dp, bottom = 10.dp)
            .height(35.dp),
        text = text,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left,
        color = Color.Black
    )
    Divider()
}

@Composable
fun ProfileText(text: String,
                onClick: () -> Unit = {}) {
    TextButton(
        onClick = { onClick.invoke() },
        Modifier
            .padding(end = 32.dp, top = 10.dp, bottom = 10.dp)
            .height(35.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Left,
            color = Color.Black)
    }
    Divider()
}

@Composable
fun ProfileImage(name: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 145.dp,
                top = 10.dp,
                end = 145.dp
            )
            .clip(CircleShape),
        painter = painterResource(id = R.drawable.profile),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
    Text(
        name,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 10.dp, bottom = 30.dp),
        color = Color.Black
    )
}

@Composable
fun switchTheme() {
    TextButton(
        onClick = {},
        Modifier
            .padding(end = 32.dp, top = 10.dp, bottom = 10.dp)
            .height(35.dp)
    ) {
        Row() {
            Text(
                "Theme",
                textAlign = TextAlign.Left,
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(15.dp))
            var checked by remember { mutableStateOf(true) }
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }
    }
    Divider()
}