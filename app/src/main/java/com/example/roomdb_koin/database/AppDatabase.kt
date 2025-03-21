package com.example.roomdb_koin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb_koin.model.User
import com.example.roomdb_koin.dao.UserDao

// AppDatabase.kt
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}