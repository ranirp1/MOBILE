package cn.shef.msc5.todo.demos.ui.buttons

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.R

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 31/10/2023 11:56
 */
class ButtonsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button()
                    ElevatedButton()
                    FilledTonalButton()
                    OutlinedButton()
                    TextButton()
                    IconButton()
                    ExtendedFloatingActionButton()
                }
            }
        }
    }
}

@Composable
fun Button() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
//        border = BorderStroke(
//            1.dp,
//            Brush.linearGradient(
//                0f to Color.Transparent,
//                1f to Color.DarkGray
//            )
//        ),
//        contentPadding = PaddingValues(
//            start = 10.dp,
//            top = 5.dp
//        ),
        content = {
            Text("Dodo ")
        }
    )
}

@Composable
fun ElevatedButton() {
    ElevatedButton(
        onClick = { /*TODO*/ }
    ) {
        Icon( // adds Icon in button
            imageVector = Icons.Outlined.Add,
            contentDescription = "Add",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Elevated Button")
    }
}

@Composable
fun FilledButton() {

}

@Composable
fun FilledTonalButton() {
    FilledTonalButton(
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Filled Tonal Button")
    }
}

@Composable
fun OutlinedButton() {
    OutlinedButton(
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Back")
    }
}

@Composable
fun TextButton() {
    TextButton(
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Text Button")
    }
}

@Composable
fun IconButton() {
    IconButton(
        onClick = { /*TODO*/ },
        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Green),
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "icon"
            )
        }
    )
}


@Composable
fun ExtendedFloatingActionButton() {
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Back")
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLargeTopAppBar() {
    Button()
//    ElevatedButton()
//    FilledTonalButton()
//    OutlinedButton()
//    TextButton()
//    ExtendedFloatingActionButton()
//    IconButton()
}



