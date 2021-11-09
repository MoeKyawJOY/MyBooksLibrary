package com.example.mybooklibrary.allbooks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybooklibrary.ALL_BOOK_KEY
import com.example.mybooklibrary.ALREADY_READ_BOOK_KEY
import com.example.mybooklibrary.FAVOURITE_BOOK_KEY
import com.example.mybooklibrary.database.BookDatabaseDao
import com.example.mybooklibrary.database.Books

class AllBooksViewModel(
    val database: BookDatabaseDao,
    application: Application,
    private val key: Int
): AndroidViewModel(application) {

    lateinit var allBooks: LiveData<List<Books>>

    init {
        allBooks = when(key){
            ALL_BOOK_KEY -> database.getAllBooks()
            FAVOURITE_BOOK_KEY -> database.getAllFavouriteBooks()
            ALREADY_READ_BOOK_KEY -> database.getAllReadBooks()
            else -> database.getAllBooks()
        }
    }



}