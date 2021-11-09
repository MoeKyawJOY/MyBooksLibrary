package com.example.mybooklibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooklibrary.database.Books
import com.example.mybooklibrary.databinding.BookListItemsBinding

const val ALL_BOOK_KEY = 1
const val FAVOURITE_BOOK_KEY = 2
const val ALREADY_READ_BOOK_KEY = 3

class BookListAdapter(private val clickListener: BookClickListener): ListAdapter<Books, BookListAdapter.ViewHolder>(BooksDiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BookListItemsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

    class ViewHolder(private val binding: BookListItemsBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Books, clickListener: BookClickListener){
            binding.book = item
            binding.bookClickListener = clickListener
            binding.executePendingBindings()
        }

    }

    class BooksDiffCallBack: DiffUtil.ItemCallback<Books>(){
        override fun areItemsTheSame(oldItem: Books, newItem: Books): Boolean {
            return oldItem.bookId  == newItem.bookId
        }

        override fun areContentsTheSame(oldItem: Books, newItem: Books): Boolean {
            return oldItem == newItem
        }
    }

    class BookClickListener(val clickListener: (book: Books) -> Unit){
        fun onClick(book: Books) = clickListener(book)
    }
}