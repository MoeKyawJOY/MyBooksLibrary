package com.example.mybooklibrary.bookdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybooklibrary.database.BookDatabaseDao
import com.example.mybooklibrary.database.Books
import kotlinx.coroutines.*

class BookDetailsViewModel(
    val database: BookDatabaseDao,
    application: Application,
    val book: Books
) : AndroidViewModel(application){

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _bookIsFavourite = MutableLiveData<Boolean>()
    val bookIsFavourite: LiveData<Boolean>
        get() = _bookIsFavourite

    private val _bookHasRead = MutableLiveData<Boolean>()
    val bookHasRead: LiveData<Boolean>
        get() = _bookHasRead

    private val _navigateToAllBooks = MutableLiveData<Boolean>()
    val navigateToAllBooks: LiveData<Boolean>
        get() = _navigateToAllBooks

    init {
        _bookHasRead.value = book.hasRead
        _bookIsFavourite.value = book.isFavourite
        _navigateToAllBooks.value = false
    }

    fun onFavClicked(){
        book.isFavourite = true
        _bookIsFavourite.value = true
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(book)
            }
        }
    }

    fun onNotFavClicked(){
        book.isFavourite = false
        _bookIsFavourite.value = false
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(book)
            }
        }
    }

    fun onReadClicked(){
        book.hasRead = true
        _bookHasRead.value = true
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(book)
            }
        }
    }

    fun onNotReadClicked(){
        book.hasRead = false
        _bookHasRead.value = false
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(book)
            }
        }
    }

    fun onDeleteClicked(){
        _navigateToAllBooks.value = true
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.deleteWithId(book.bookId)
            }
        }
    }

    fun onNavigateComplete(){
        _navigateToAllBooks.value = false
    }


}