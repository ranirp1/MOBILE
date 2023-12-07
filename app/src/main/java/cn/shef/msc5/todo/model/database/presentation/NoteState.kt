package cn.shef.msc5.todo.model.database.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cn.shef.msc5.todo.model.dao.TaskDAO

data class NoteState(

    val notes: List<TaskDAO> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf("")
    )