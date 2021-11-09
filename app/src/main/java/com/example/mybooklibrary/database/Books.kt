package com.example.mybooklibrary.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "all_books_table")
data class Books(
    @PrimaryKey(autoGenerate = true)
    val bookId: Int = 0,

    @ColumnInfo(name = "book_title")
    var title: String = "No Title",

    @ColumnInfo(name = "author_name")
    var author: String = "Uknown",

    @ColumnInfo(name = "book_pages")
    var pages: Int = 0,

    @ColumnInfo(name = "book_description")
    var description: String = "No Description",

    @ColumnInfo(name = "have_read")
    var hasRead: Boolean = false,

    @ColumnInfo(name = "is_favourite")
    var isFavourite: Boolean = false,

): Parcelable