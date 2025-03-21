package com.example.roomdb_koin.di
import androidx.room.Room
import com.example.roomdb_koin.database.AppDatabase
import com.example.roomdb_koin.repository.UserRepository
import com.example.roomdb_koin.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Singleton cho AppDatabase
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "user_database"
        ).build()
    }

    // Singleton cho UserDao
    single { get<AppDatabase>().userDao() }

    // Singleton cho UserRepository
    single { UserRepository(get()) }

    // ViewModel cho UserViewModel
    viewModel { UserViewModel(get()) }
}