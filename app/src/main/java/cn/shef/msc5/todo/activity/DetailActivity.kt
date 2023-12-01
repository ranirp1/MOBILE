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
import cn.shef.msc5.todo.ui.view.DetailScreen

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 13/11/2023 13:22
 */

class DetailActivity : BaseActivity() {

    private val TAG = "DetailActivity"
    val mainViewModel by lazy {
        MainViewModelFactory(ScreenTypeEnum.OTHER_SCREEN, AppDatabase.INSTANCE.getTaskDAO()).
        create(MainViewModel::class.java)
    }


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
                    DetailScreen(mainViewModel)
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





