package com.example.mybooklibrary.database


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDatabaseDao {

    @Insert
    suspend fun insert(book: Books)

    @Update
    suspend fun update(book: Books)

    @Query("SELECT * FROM all_books_table WHERE bookId = :key")
    suspend fun get(key: Int): Books?

    @Query("DELETE FROM all_books_table")
    suspend fun clear()

    @Query("SELECT * FROM all_books_table")
    fun getAllBooks(): LiveData<List<Books>>

    @Query("SELECT * FROM all_books_table WHERE have_read = :value")
    fun getAllReadBooks(value: Boolean = true): LiveData<List<Books>>

    @Query("SELECT * FROM all_books_table WHERE is_favourite = :value")
    fun getAllFavouriteBooks(value: Boolean = true): LiveData<List<Books>>

    @Query("SELECT * FROM all_books_table WHERE bookId = :id")
    fun getNightWithId(id: Int): LiveData<Books>

    @Query("DELETE FROM all_books_table WHERE bookId = :id")
    suspend fun deleteWithId(id:Int)





}