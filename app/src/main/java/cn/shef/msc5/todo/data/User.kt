package com.example.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//represents a table within the databse
@Entity (tableName = "user_table")
data class User (
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)