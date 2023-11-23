//package cn.shef.msc5.todo.demos.dao
//
//import android.content.res.Configuration
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.LifecycleCoroutineScope
//
//import androidx.lifecycle.lifecycleScope
//import kotlinx.coroutines.launch
//
///**
// * @author Zhecheng Zhao
// * @email zzhao84@sheffield.ac.uk
// * @date Created in 31/10/2023 10:48
// */
//class RoomActivity : ComponentActivity() {
//
//    private val TAG = "RoomActivity"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            MaterialTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MainScreen(lifecycleScope)
//                }
//            }
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.v(TAG, "onStart")
//        //TODO request permissions
//
//    }
//
//}
//
//@Composable
//fun MainScreen(lifecycleScope: LifecycleCoroutineScope) {
//    //get context
//    val context = LocalContext.current
//    Column {
//        Button(onClick = {
//            lifecycleScope.launch {
//                val toDoDAO = ToDoDatabase.dataBase.getToDoDAO()
//                toDoDAO.insert(ToDo(0,"11","111"));
//                toDoDAO.insert(ToDo(0,"22","222"));
//            }
//
//        }) {
//            Text("INSERT")
//        }
//        Button(onClick = {
//            //jump to main activity
//            lifecycleScope.launch {
//                val toDoDAO = ToDoDatabase.dataBase.getToDoDAO()
//                val allStudent = toDoDAO.getAllStudent();
//
//                if (allStudent != null) {
//                    for (v in allStudent) {
//                        Log.d("RoomActivity", "students: $v")
//                    }
//                }
//            }
//
//        }) {
//            Text("getAllStudent")
//        }
//    }
//}
//
//
//@Preview(name = "Light theme")
//@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun PreviewMainScreen() {
//}
