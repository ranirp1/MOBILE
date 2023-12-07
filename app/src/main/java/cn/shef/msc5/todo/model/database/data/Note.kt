package cn.shef.msc5.todo.model.database.data

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Note(
    val title: String,
    val description: String,
    val dateadded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int=0
)
