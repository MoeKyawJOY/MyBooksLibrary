package com.example.mybooklibrary.allbooks

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooklibrary.database.BookDatabaseDao
import java.lang.IllegalArgumentException

class AllBooksViewModelFactory(
    private val database: BookDatabaseDao,
    private val application: Application,
    private val key: Int
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AllBooksViewModel::class.java)){
            return AllBooksViewModel(database, application, key) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}