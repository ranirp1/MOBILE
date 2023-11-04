package cn.shef.msc5.todo

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cn.shef.msc5.todo.base.BaseActivity
import cn.shef.msc5.todo.base.BaseFloatingActionBar
import cn.shef.msc5.todo.base.BaseScaffold
import cn.shef.msc5.todo.demos.ui.navigation.getIconForScreen
import cn.shef.msc5.todo.model.viewmodel.UserViewModel
import cn.shef.msc5.todo.ui.screen.HomeScreen
import cn.shef.msc5.todo.utilities.Constants.Companion.NAVIGATION_HOME
import cn.shef.msc5.todo.utilities.Constants.Companion.NAVIGATION_PROFILE
import cn.shef.msc5.todo.utilities.Constants.Companion.NAVIGATION_SEARCH

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 02/11/2023 06:45
 */
class MainActivity : BaseActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val context = LocalContext.current
        var userViewModel: UserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(UserViewModel::class.java)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(this, userViewModel)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //TODO request permissions
        Log.d(TAG, "onStart: ")
    }

}

@Composable
fun MainScreen(context : Context, userViewModel: UserViewModel) {
    //get context
    val items = listOf(
        NAVIGATION_HOME,
        NAVIGATION_SEARCH,
        NAVIGATION_PROFILE
    )
    var selectedItem by remember { mutableStateOf(items.first()) }
    BaseScaffold(
        bottomBar = {
            NavigationBar() {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(getIconForScreen(item), contentDescription = null) },
                        label = { Text(item) },
                        selected = item == selectedItem,
                        onClick = {
                            selectedItem = item
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        },
        floatingActionButton = {
            BaseFloatingActionBar()
        }
    ) {
        when (selectedItem) {
            NAVIGATION_HOME -> HomeScreen(context, userViewModel)
            NAVIGATION_SEARCH -> EmptyScreen(context)
            NAVIGATION_PROFILE -> EmptyScreen(context)
        }
    }
}

@Composable
fun EmptyScreen(context : Context) {
    Column(
//        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )
        Button(
            onClick = {
                context.startActivity(Intent(context, DetailActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
            content = {
                Text("Dodo ")
            }
        )
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {

//    MainScreen(MainActivity())
}
