package cn.shef.msc5.todo.model.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import cn.shef.msc5.todo.model.dao.TaskDAO

@Database (
    entities = [TaskDAO::class],
    version = 1
)

abstract class NotesDatabase: RoomDatabase() {
    abstract val dao: NoteDao
}