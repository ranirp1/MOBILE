package cn.shef.msc5.todo.ui.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.LocationActivity
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.utilities.GeneralUtil

@Composable
fun SettingScreen(context: Context, mainViewModel: MainViewModel){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF6650a4)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(8.dp))
        Image(painterResource(id = R.drawable.profile),null)
        Text("UserName",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top=10.dp),
            color = Color.White
        )
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
                .padding(
                    horizontal = 15.dp
                )
        ){
            Text(
                text = "My Account",
                textAlign = TextAlign.Left,
                color = Color.Black
            )
            TextButton(onClick = { /*TODO*/ },
                Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(35.dp)
            ) {
                Text(text = "UserName")
            }
            TextButton(onClick = {},
                Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(35.dp)
            ) { Row(){
                Text("Theme")
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
            val context = LocalContext.current
            TextButton(onClick = {
                val intent = Intent(context, LocationActivity::class.java)
                GeneralUtil.startActivity2(context, intent)
            },
                Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(35.dp)
            ) {
                Text("Location")
            }
            Text(
                text = "About",
                textAlign = TextAlign.Left,
                color = Color.Black
            )
            TextButton(onClick = { /*TODO*/ },
                Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(35.dp)
            ) {
                Text(text = "Version 1.0")
            }
            TextButton(onClick = { /*TODO*/ },
                Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                    .height(35.dp)
            ) {
                Text(text = "Privacy Policy")
            }
        }
    }
}
