package com.example.mybooklibrary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_books_table")
data class Books(
    @PrimaryKey(autoGenerate = true)
    val bookId: Int,

    @ColumnInfo(name = "book_title")
    val title: String,

    @ColumnInfo(name = "author_name")
    val author: String,

    @ColumnInfo(name = "book_pages")
    val pages: Int,

    @ColumnInfo(name = "book_description")
    val description: String,

    @ColumnInfo(name = "have_read")
    var hasRead: Boolean = false,

    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false,

)