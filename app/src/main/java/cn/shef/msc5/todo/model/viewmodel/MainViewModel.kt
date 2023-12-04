package cn.shef.msc5.todo.model.viewmodel

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cn.shef.msc5.todo.model.SortOrder
import cn.shef.msc5.todo.model.SortType
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.TaskListState
import cn.shef.msc5.todo.model.dao.TaskDAO
import cn.shef.msc5.todo.utilities.DateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
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
    val sortType = SortType.DueDate(SortOrder.Ascending)
    val taskListFlow: StateFlow<List<Task>> get() = _taskListFlow
    private var postExecute: (() -> Unit)? = null

    val dateConverter = DateConverter()

    var state by mutableStateOf(TaskListState())

    init {
        state = state.copy(isLoading = true)
        loadTaskList()
    }

    private fun loadTaskList() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(2500)
            taskDAO.getAllTasks().collect {
                taskList = it.toMutableStateList()

                Log.d("MainViewModel", taskList.size.toString())
                _taskListFlow.value = taskList

                state = state.copy(isLoading = false,
                    data = _taskListFlow.value)

                postExecute?.invoke()
            }
        }
    }

    fun setLevel(index: Int, value: Int) {
        val editedTask = taskList[index].copy(priority = value)
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.update(editedTask)
            postExecute = null
        }
    }

    fun addTask(
        title: String,
        description: String,
        level: Int,
        longitude: Float,
        latitude: Float,
        imageUrl: String,
        gmtCreated: Date,
        gmtModified: Date,
        dueTime: Date,
        parentId: Int,
        remark: String,
        postInsert: (() -> Unit)? = null
    ) {
        val id = taskList.lastOrNull()?.id ?: -1
        val todoItem = Task(
            id + 1, title, 1, description, level, longitude, latitude,
            imageUrl, dueTime, parentId, gmtCreated, gmtModified, 0, remark
        )
        viewModelScope.launch(Dispatchers.IO) {
//            state = state.copy(isLoading = true)
//            delay(500)
            taskDAO.insert(todoItem)
//            state = state.copy(isLoading = false)
            postExecute = postInsert
        }
    }

    fun delete(
        task: Task
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.delete(task)
        }
    }

    fun sortAllTasks(sortType: SortType) {
        taskDAO.getAllTasks().map { tasks ->
            when (sortType.sortOrder) {
                is SortOrder.Ascending -> {
                    when (sortType) {
                        is SortType.Priority -> tasks.sortedBy { it.priority }
                        is SortType.DueDate -> tasks.sortedBy { it.dueTime }
                        is SortType.Location -> tasks.sortedBy { it.latitude }// ToDo by distance to location
                    }
                }

                is SortOrder.Descending -> {
                    when (sortType) {
                        is SortType.Priority -> tasks.sortedByDescending { it.priority }
                        is SortType.DueDate -> tasks.sortedByDescending { it.dueTime }
                        is SortType.Location -> tasks.sortedByDescending { it.latitude }// ToDo by distance to location
                    }
                }
            }
        }
    }

    fun sortTasksByDate(sortType: SortType, date: Date) {
        val selectedDate = dateConverter.formatDateYear(date)
        taskDAO.getAllTasksByDate(selectedDate).map { tasks ->
            when (sortType.sortOrder) {
                is SortOrder.Ascending -> {
                    when (sortType) {
                        is SortType.Priority -> tasks.sortedBy { it.priority }
                        is SortType.DueDate -> tasks.sortedBy { it.dueTime }
                        is SortType.Location -> tasks.sortedBy { it.latitude }// ToDo by distance to location
                    }
                }

                is SortOrder.Descending -> {
                    when (sortType) {
                        is SortType.Priority -> tasks.sortedByDescending { it.priority }
                        is SortType.DueDate -> tasks.sortedByDescending { it.dueTime }
                        is SortType.Location -> tasks.sortedByDescending { it.latitude }// ToDo by distance to location
                    }
                }
            }
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
