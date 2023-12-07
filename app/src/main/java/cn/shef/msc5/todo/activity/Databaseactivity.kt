package cn.shef.msc5.todo.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import cn.shef.msc5.todo.model.database.data.NotesDatabase
import cn.shef.msc5.todo.model.database.presentation.AddNoteScreen
import cn.shef.msc5.todo.model.database.presentation.NotesScreen
import cn.shef.msc5.todo.model.database.presentation.NotesViewModel

class Databaseactivity : ComponentActivity () {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by viewModels<NotesViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NotesViewModel(database.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface (
                modifier = Modifier.fillMaxSize() )
            {
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "NotesScreen" ) {
                    composable("NotesScreen") {
                        NotesScreen (
                            state = state,
                            navController = navController,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable("AddNoteScreen") {
                        AddNoteScreen (
                            state = state,
                            navController = navController,
                            onEvent = viewModel::onEvent
                        )
                    }
                }

            }
        }
    }


}