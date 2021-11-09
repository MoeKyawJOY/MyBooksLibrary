package com.example.mybooklibrary.addbook

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooklibrary.database.BookDatabaseDao
import java.lang.IllegalArgumentException

class AddBookViewModelFactory(
    val database: BookDatabaseDao,
    val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddBookViewModel::class.java)){
            return AddBookViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}