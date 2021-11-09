package com.example.mybooklibrary.updatebook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mybooklibrary.database.BookDatabaseDao
import com.example.mybooklibrary.database.Books
import kotlinx.coroutines.*

class UpdateBookViewModel(
    val dataSource: BookDatabaseDao,
    application: Application,
    val book: Books,
    // key is for navigating to all books fragment
): AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val navigateToAllBooks = MutableLiveData<Boolean>()

    init {
        navigateToAllBooks.value = false
    }

    fun onUpdateClicked(){
        navigateToAllBooks.value = true
        onUpdate()

    }

    private fun onUpdate(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                dataSource.update(book)
            }
        }
    }

    fun onNavigateComplete(){
        navigateToAllBooks.value = false
    }
}