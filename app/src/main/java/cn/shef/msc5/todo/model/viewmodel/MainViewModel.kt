package cn.shef.msc5.todo.model.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.dao.TaskDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    fun addTask(title: String, level: Int, postInsert: (() -> Unit)? = null) {
        val id = taskList.lastOrNull()?.id ?: -1
        val todoItem = Task(id + 1, title, level, "")
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.insert(todoItem)
            postExecute = postInsert
        }
    }
}