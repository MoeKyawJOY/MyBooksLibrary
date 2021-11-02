package com.example.mybooklibrary.allbooks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybooklibrary.database.BookDatabaseDao
import com.example.mybooklibrary.database.Books

class AllBooksViewModel(
    val database: BookDatabaseDao,
    application: Application
): AndroidViewModel(application) {

    val allBooks = database.getAllBooks()



}