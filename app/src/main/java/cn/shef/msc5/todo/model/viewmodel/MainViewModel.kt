package cn.shef.msc5.todo.model.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cn.shef.msc5.todo.model.dto.Location
import cn.shef.msc5.todo.model.enums.ScreenTypeEnum
import cn.shef.msc5.todo.model.SortOrder
import cn.shef.msc5.todo.model.enums.SortType
import cn.shef.msc5.todo.model.Task
import cn.shef.msc5.todo.model.enums.TaskListState
import cn.shef.msc5.todo.model.enums.TaskStateEnum
import cn.shef.msc5.todo.model.dao.TaskDAO
import cn.shef.msc5.todo.model.dto.SubTask
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

class MainViewModel(
    private val userId: Int,
    private val screenType: ScreenTypeEnum,
    private val taskDAO: TaskDAO
) : ViewModel() {

    private var taskList = mutableStateListOf<Task>()
    private val _taskListFlow = MutableStateFlow(taskList)

    val dateConverter = DateConverter()
    var sortType : SortType = SortType.Priority(SortOrder.Ascending)
    var date = Date(dateConverter.formatDateYear(System.currentTimeMillis()))

    val taskListFlow: StateFlow<List<Task>> get() = _taskListFlow
    private var postExecute: (() -> Unit)? = null

    var state by mutableStateOf(TaskListState())
    var notInitial by mutableStateOf(false)

    var location by mutableStateOf(Location())

    init {
        state = state.copy(isLoading = true)
        when (screenType) {
            ScreenTypeEnum.HOME_SCREEN -> loadTaskListByDate()
            ScreenTypeEnum.PROGRESS_UNFINISHED -> loadTaskByState(TaskStateEnum.UNFINISHED.level)
            ScreenTypeEnum.PROGRESS_ISCOMPLETED -> loadTaskByState(TaskStateEnum.ISCOMPLETED.level)
            ScreenTypeEnum.OTHER_SCREEN -> loadTaskList()
        }
    }

    private fun loadTaskByState(taskstate: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(500)
            taskDAO.getTasksList(taskstate, userId).collect{
                taskList = it.toMutableStateList()
                _taskListFlow.value = taskList
                state = state.copy(isLoading = false,
                    data = _taskListFlow.value)
                postExecute?.invoke()
            }
        }
    }
    private fun loadTaskList() {
        sortAllTasks(sortType)
    }

    private fun loadTaskListByDate() {
        sortTasksByDate(sortType, date)
    }

    fun notInitialLaunch() {
        notInitial = true
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
        userId: Int,
        description: String,
        priority: Int,
        longitude: Double,
        latitude: Double,
        imageUrl: String,
        gmtCreated: Date,
        gmtModified: Date,
        dueTime: Date,
        parentId: Int,
        state: Int,
        subTask: List<SubTask>,
        postInsert: (() -> Unit)? = null
    ) {
        val id = taskList.lastOrNull()?.id ?: -1
        val todoItem = Task(
            id + 1, title, userId, description, priority, longitude, latitude,
            imageUrl, dueTime, parentId, gmtCreated, gmtModified, 0, state, subTask
        )
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.insert(todoItem)
            postExecute = postInsert
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.delete(task)
        }
    }

    fun markAsDone(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            taskDAO.updateComplete(task)
        }
    }

    fun markAsUndone(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            taskDAO.updateIncomplete(task)
        }
    }

    fun getTask(id: Int):Task?{
        return taskDAO.findByPrimaryKey(id)
    }

    fun duplicate(
        task: Task,
        gmtCreated: Date,
        gmtModified: Date,
        postInsert: (() -> Unit)? = null
    ){
        val id = taskList.lastOrNull()?.id ?: -1
        val todoItem = Task(
            id + 1, task.title, 1, task.description, task.priority, task.longitude, task.latitude,
            task.imageUrl, task.dueTime, task.parentId, gmtCreated, gmtModified, 0, TaskStateEnum.UNFINISHED.level, task.subTasks
        )
        viewModelScope.launch(Dispatchers.IO) {
            taskDAO.insert(todoItem)
            postExecute = postInsert
        }
    }

    fun sortAllTasks(sortTypeSelected: SortType) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(500)
            taskDAO.getAllTasks(userId).map { tasks ->
                when (sortTypeSelected.sortOrder) {
                    is SortOrder.Ascending -> {
                        when (sortTypeSelected) {
                            is SortType.Priority -> tasks.sortedBy { it.priority }
                            is SortType.DueDate -> tasks.sortedBy { it.dueTime }
                            is SortType.Location -> tasks.sortedBy { it.latitude }// ToDo by distance to location
                        }
                    }

                    is SortOrder.Descending -> {
                        when (sortTypeSelected) {
                            is SortType.Priority -> tasks.sortedByDescending { it.priority }
                            is SortType.DueDate -> tasks.sortedByDescending { it.dueTime }
                            is SortType.Location -> tasks.sortedByDescending { it.latitude }// ToDo by distance to location
                        }
                    }
                }
            }.collect{
                taskList = it.toMutableStateList()
                _taskListFlow.value = taskList
                state = state.copy(isLoading = false,
                    data = _taskListFlow.value)
                postExecute?.invoke()
            }
        }
    }

    fun sortTasksByDate(sortType: SortType, date: Date) {
        val selectedDate = dateConverter.converterDate(date)
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(500)
            taskDAO.getAllTasksByDate(selectedDate, userId).map { tasks ->
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
                state = state.copy(isLoading = false,
                    data = _taskListFlow.value)
                postExecute?.invoke()
            }
        }
    }
}

class MainViewModelFactory(
    private val userId: Int,
    private val screenType: ScreenTypeEnum,
    private val taskDAO: TaskDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(userId, screenType, taskDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}