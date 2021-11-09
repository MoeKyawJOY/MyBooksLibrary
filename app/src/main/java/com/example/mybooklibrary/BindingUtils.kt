package com.example.mybooklibrary

import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.mybooklibrary.database.Books

@BindingAdapter("addTitle")
fun TextView.addBookTitle(item: Books?){
    item?.let {
        text = item.title
    }
}

@BindingAdapter("addAuthor")
fun TextView.addBookAuthor(item: Books?){
    item?.let {
        text = item.author
    }
}

@BindingAdapter("addPages")
fun TextView.addBookPages(item: Books?){
    item?.let {
        text = item.pages.toString()
    }
}

@BindingAdapter("addId")
fun TextView.addBookId(item: Books?){
    item?.let {
        text = item.bookId.toString()
    }
}

@BindingAdapter("addDescription")
fun TextView.addDescription(item: Books?){
    item?.let {
        text = item.description
    }
}

// for favourite and already read text in book details fragment
@BindingAdapter("setVisibilityWithValue")
fun TextView.setVisibilityWithValue(isTrue: Boolean){
    visibility = if(isTrue) TextView.VISIBLE else TextView.GONE
}


