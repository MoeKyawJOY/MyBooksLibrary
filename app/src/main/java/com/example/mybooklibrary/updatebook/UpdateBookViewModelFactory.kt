package com.example.mybooklibrary.updatebook

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooklibrary.database.BookDatabaseDao
import com.example.mybooklibrary.database.Books
import java.lang.IllegalArgumentException

class UpdateBookViewModelFactory(
    val dataSource: BookDatabaseDao,
    val application: Application,
    val book: Books,
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpdateBookViewModel::class.java)){
            return UpdateBookViewModel(dataSource, application, book) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}