package cn.shef.msc5.todo.ui.view

import android.app.Activity
import android.content.Intent
import android.text.Layout
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.activity.MainActivity
import cn.shef.msc5.todo.utilities.GeneralUtil
import cn.shef.msc5.todo.utilities.SharedPreferenceManger

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 07/12/2023 12:00
 */
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    var username = remember { mutableStateOf("") }
    val intent = Intent(context, MainActivity::class.java)
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.padding(0.dp, 18.dp)) {
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text(text = "Username") },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(Modifier.padding(10.dp, 25.dp)) {
                Button(
                    onClick = {
                        val sharedPreferenceManger = SharedPreferenceManger(context)
                        sharedPreferenceManger.userId = 1
                        sharedPreferenceManger.userName = username.value
                        GeneralUtil.startActivity2(context, intent)
                        GeneralUtil.finishActivity2(context)
                    },
                    modifier = Modifier.size(120.dp, 40.dp)
                ) {
                    Text(
                        text = "Login",
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif,
                            )
                    )
                }
            }
        }
    }
}