package com.example.todo.data

import androidx.lifecycle.LiveData


// Repository class abstracts access to multiple data sources
class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>>

    suspend fun addUser(user: User){
        userDao.addUser(user)

    }
}