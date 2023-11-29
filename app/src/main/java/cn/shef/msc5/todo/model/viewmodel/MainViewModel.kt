package cn.shef.msc5.todo.model.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cn.shef.msc5.todo.model.SortOrder
import cn.shef.msc5.todo.model.SortType
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.dao.TaskDAO
import cn.shef.msc5.todo.utilities.DateConverter
import kotlinx.coroutines.Dispatchers
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

    // not sure
    var sortType = SortType.Priority(SortOrder.Ascending)
    var date = Date(System.currentTimeMillis())

    val taskListFlow: StateFlow<List<Task>> get() = _taskListFlow
    private var postExecute: (() -> Unit)? = null
    val dateConverter = DateConverter()

    init {
        loadTaskList()
        // not sure if should add it like this
        sortType = SortType.Priority(SortOrder.Ascending)
        date = Date(System.currentTimeMillis())
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
        val editedTask = taskList[index].copy(priority = value)
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.update(editedTask)
            postExecute = null
        }
    }

    fun addTask(
        title: String,
        description: String,
        priority: Int,
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
            id + 1, title, 1, description, priority, longitude, latitude,
            imageUrl, dueTime, parentId, gmtCreated, gmtModified, 0, remark
        )
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.insert(todoItem)
            postExecute = postInsert
        }
    }

    // it seemed to only sort by priority
    fun sortAllTasks(sortType: SortType) {
        viewModelScope.launch {
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

            }.collect{
                taskList = it.toMutableStateList()
                _taskListFlow.value = taskList
                postExecute?.invoke()
            }
        }
    }

    // it seemed to only sort by priority
    fun sortTasksByDate(sortType: SortType, date: Date) {
        val selectedDate = dateConverter.converterDate(date)
        viewModelScope.launch {
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
            }.collect{
                taskList = it.toMutableStateList()
                _taskListFlow.value = taskList
                postExecute?.invoke()
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
