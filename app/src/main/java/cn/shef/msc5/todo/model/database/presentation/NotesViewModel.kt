package cn.shef.msc5.todo.model.database.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.shef.msc5.todo.model.database.data.Note
import cn.shef.msc5.todo.model.database.data.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(private val dao: NoteDao): ViewModel () {
    private val isSortedbyDateadded = MutableStateFlow(true)

    private val notes =
        isSortedbyDateadded.flatMapLatest { sort ->
            if (sort) {
                dao.getNotesOrderedByDateAdded()
            } else {
                dao.getNotesOrderedByTitle()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(NoteState())
    val state =
        combine(_state, isSortedbyDateadded, notes) { state, isSortedbyDateadded, notes ->
            state.copy(
                notes = notes
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            is NotesEvent.SaveNote -> {
                val note = Note(
                    title = state.value.title.value,
                    description = state.value.description.value,
                    dateadded = System.currentTimeMillis()
                )

                viewModelScope.launch {
                    dao.UpsertNote(note)
                }

                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        description = mutableStateOf("")
                    )
                }
            }

            NotesEvent.SortNotes -> {
                isSortedbyDateadded.value = !isSortedbyDateadded.value

            }
        }


    }
}