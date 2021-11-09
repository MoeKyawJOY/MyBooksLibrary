package com.example.mybooklibrary.bookdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooklibrary.database.BookDatabaseDao
import com.example.mybooklibrary.database.Books
import java.lang.IllegalArgumentException

class BookDetailsViewModelFactory(
    private val databaseDao: BookDatabaseDao,
    val application: Application,
    val book: Books,
    ) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BookDetailsViewModel::class.java)){
            return BookDetailsViewModel(databaseDao, application, book) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}