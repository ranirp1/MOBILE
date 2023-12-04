package cn.shef.msc5.todo.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cn.shef.msc5.todo.base.BaseActivity
import cn.shef.msc5.todo.model.ScreenTypeEnum
import cn.shef.msc5.todo.model.database.AppDatabase
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import cn.shef.msc5.todo.model.viewmodel.MainViewModelFactory
import cn.shef.msc5.todo.ui.view.ViewScreen

/**
 * @author Raghav Chhabra
 * @email rchhabra1@sheffield.ac.uk
 */
class ViewActivity : BaseActivity() {

    private val TAG = "ViewActivity"
    val mainViewModel by lazy {
        MainViewModelFactory(ScreenTypeEnum.OTHER_SCREEN, AppDatabase.INSTANCE.getTaskDAO()).
        create(MainViewModel::class.java)
    }
    var id = taskId
    private var tas=mainViewModel.gettask(taskId)

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    tas?.let { ViewScreen(it,mainViewModel) }
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





