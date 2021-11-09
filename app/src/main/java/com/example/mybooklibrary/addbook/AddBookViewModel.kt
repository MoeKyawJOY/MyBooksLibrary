package com.example.mybooklibrary.addbook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybooklibrary.database.BookDatabaseDao
import com.example.mybooklibrary.database.Books
import kotlinx.coroutines.*

class AddBookViewModel(
    val database: BookDatabaseDao,
    application: Application
): AndroidViewModel(application) {

    private var viewModelJob = Job()

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var currentBook = MutableLiveData<Books>()

    fun onAddButtonClicked(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                currentBook.value?.let {
                    database.insert(it)
                }
            }
        }
    }



}