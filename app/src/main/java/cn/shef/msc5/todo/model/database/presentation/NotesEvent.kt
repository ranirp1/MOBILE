package cn.shef.msc5.todo.model.database.presentation

import cn.shef.msc5.todo.model.dao.TaskDAO

sealed interface NotesEvent {
    object SortNotes: NotesEvent
    data class DeleteNote(val note: TaskDAO): NotesEvent

    data class SaveNote(
        val title: String,
        val description: String
    ): NotesEvent

}
