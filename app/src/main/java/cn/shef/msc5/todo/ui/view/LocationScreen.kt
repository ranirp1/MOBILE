package cn.shef.msc5.todo.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.R
import cn.shef.msc5.todo.base.component.BaseScaffold

@Composable
fun LocationScreen(){

    var text by remember { mutableStateOf("") }
    var fabVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    BaseScaffold(
        showTopBar = true,
        showNavigationIcon = true,
        showFirstIcon = false,
        showSecondIcon = false,
        navigationIIcon = Icons.Filled.ArrowBack,
        title = stringResource(cn.shef.msc5.todo.R.string.todo_add_location),
        hostState = snackbarHostState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6650a4), RectangleShape),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        horizontal = 20.dp
                    )
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Icon(
                        Icons.Rounded.Home,
                        contentDescription = stringResource(id = R.string.abc_action_bar_home_description)
                    )
                    Text(
                        fontSize = 19.sp,
                        text = "Home Location",
                        textAlign = TextAlign.Left,
                        color = Color.Black
                    )
                }
                OutlinedTextField(
                    value = text,
                    onValueChange = {text = it},
                    label = { Text("location") })
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Icon(
                        Icons.Rounded.Place,
                        contentDescription = null
                    )
                    Text(
                        fontSize = 19.sp,
                        text = "Work Location",
                        textAlign = TextAlign.Left,
                        color = Color.Black
                    )
                }
                OutlinedTextField(
                    value = text,
                    onValueChange = {text = it},
                    label = { Text("location") })
            }
        }
    }

}