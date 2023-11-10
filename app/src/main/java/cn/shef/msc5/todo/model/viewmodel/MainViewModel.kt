package cn.shef.msc5.todo.model.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.dao.TaskDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.sql.Date

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 05/11/2023 15:52
 */
class MainViewModel(private val taskDAO: TaskDAO) : ViewModel() {

    private var taskList = mutableStateListOf<Task>()
    private val _taskListFlow = MutableStateFlow(taskList)

    val taskListFlow: StateFlow<List<Task>> get() = _taskListFlow
    private var postExecute: (() -> Unit)? = null

    init {
        loadTaskList()
    }

    private fun loadTaskList() {
        viewModelScope.launch {
            taskDAO.getAllTasks().collect {
                taskList = it.toMutableStateList()
                _taskListFlow.value = taskList
                postExecute?.invoke()
            }
        }
    }

    fun setLevel(index: Int, value: Int) {
        val editedTask = taskList[index].copy(level = value)
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.update(editedTask)
            postExecute = null
        }
    }

    fun addTask(title: String, description: String, level: Int, longitude: Float, latitude: Float,
                imageUrl: String, gmtCreate: Date, remark: String, postInsert: (() -> Unit)? = null) {
        val id = taskList.lastOrNull()?.id ?: -1
        val todoItem = Task(id + 1, title, 1, description, level, longitude, latitude,
             imageUrl, gmtCreate, 1, remark)
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.insert(todoItem)
            postExecute = postInsert
        }
    }
}

class MainViewModelFactory(
    private val taskDAO: TaskDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(taskDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
