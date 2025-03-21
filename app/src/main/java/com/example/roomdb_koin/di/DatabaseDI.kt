package com.example.roomdb_koin.di

import android.content.Context
import androidx.room.Room
import com.example.roomdb_koin.database.AppDatabase

fun provideDatabase(context : Context) = Room.databaseBuilder(context, AppDatabase::class.java, "user_database").build()

fun userDAO(db : AppDatabase) = db.userDao()