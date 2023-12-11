package cn.shef.msc5.todo.ui.view

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.LocationActivity
import cn.shef.msc5.todo.activity.LoginActivity
import cn.shef.msc5.todo.base.component.BaseScaffold
import cn.shef.msc5.todo.utilities.AppInfoUtil
import cn.shef.msc5.todo.utilities.GeneralUtil
import cn.shef.msc5.todo.utilities.SharedPreferenceManger

@ExperimentalAnimationApi
@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val sharedPreferenceManger = SharedPreferenceManger(context)
    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = false,
        secondIcon = Icons.Default.Sort,
        showFirstIcon = false,
        showSecondIcon = false,
        title = stringResource(R.string.todo_profile),
        hostState = snackbarHostState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            sharedPreferenceManger.getStringValue("userName")?.let { it1 -> ProfileImage(it1) }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(
                        horizontal = 15.dp
                    )
            ) {
                HeaderText(text = stringResource(R.string.profile_account))
//                switchTheme()
                ProfileText(stringResource(R.string.profile_location),
                    onClick = {
                        val intent = Intent(context, LocationActivity::class.java)
                        GeneralUtil.startActivitySlideIn(context, intent)
                    })
                HeaderText(text = stringResource(R.string.profile_about))
                ProfileText(
                    stringResource(R.string.profile_version) + "        " +
                            AppInfoUtil.getAppVersionName(context),
                    enabled = false
                )
                ProfileText(stringResource(R.string.profile_policy),
                    enabled = false)
                ProfileText(stringResource(R.string.profile_logout),
                    onClick = {
                        val sharedPreferenceManger = SharedPreferenceManger(context)
                        sharedPreferenceManger.isLogin = false
                        GeneralUtil.finishActivitySlideOut(context)
                        val intent = Intent(context, LoginActivity::class.java)
                        GeneralUtil.startActivitySlideIn(context, intent)
                    })
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
    )
    Divider()
}

@Composable
fun ProfileText(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button
            )
    ) {
        Text(
            text = text,
            Modifier
                .padding(start = 10.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(25.dp),
            textAlign = TextAlign.Left
        )

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
    )
}
