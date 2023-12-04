package cn.shef.msc5.todo.ui.view.state

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.activity.DetailActivity
import cn.shef.msc5.todo.utilities.GeneralUtil

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 01/12/2023 20:16
 */
@Composable
fun EmptyScreen(context : Context) {
    Column(
        modifier =  Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            fontSize = 35.sp,
            text = stringResource(id = R.string.home_empty),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Button(
            onClick = {
                val intent = Intent(context, DetailActivity::class.java)
                GeneralUtil.startActivity2(context, intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
            content = {
                Text(stringResource(id = R.string.todo_add_new_task))
            }
        )
    }
}


@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewEmptyScreen() {
//    EmptyScreen()
}