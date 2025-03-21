package com.example.roomdb_koin.repository

import androidx.lifecycle.LiveData
import com.example.roomdb_koin.dao.UserDao
import com.example.roomdb_koin.model.User

// UserRepository.kt
class UserRepository (private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }
}