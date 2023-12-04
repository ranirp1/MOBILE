package cn.shef.msc5.todo.ui.view.state

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 01/12/2023 20:17
 */
@Composable
fun ErrorScreen() {
    Column {
        Icon(
            Icons.Rounded.Warning,
            contentDescription = "Localized description",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )
        Text(
            "Something went wrong. Please try again later",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewErrorScreen() {
    ErrorScreen()
}
