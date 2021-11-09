package com.example.mybooklibrary.addbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.mybooklibrary.database.BookDatabase
import com.example.mybooklibrary.database.Books
import com.example.mybooklibrary.databinding.FragmentAddBookBinding



class AddBookFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAddBookBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao

        val addBookViewModelFactory = AddBookViewModelFactory(dataSource, application)
        val addBookViewModel = ViewModelProvider(this, addBookViewModelFactory).get(AddBookViewModel::class.java)

        binding.addBookViewModel = addBookViewModel
        binding.lifecycleOwner = this

        binding.btnAdd.setOnClickListener {
            addBookToViewModel(binding, addBookViewModel)

            addBookViewModel.onAddButtonClicked()
            Toast.makeText(context, "Add Successed", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AddBookFragmentDirections.actionAddBookFragmentToMainFragment())
        }

        return binding.root
    }

    private fun addBookToViewModel(
        binding: FragmentAddBookBinding,
        addBookViewModel: AddBookViewModel
    ) {
        val bookTitle = binding.edtBookTitle.text.toString()
        val bookAuthor = binding.edtBookAuthor.text.toString()
        val bookPages = binding.edtBookPages.text.toString().toInt()
        val bookDesc = binding.edtBookDescription.text.toString()
        val book = Books()
        book.title = bookTitle
        book.author = bookAuthor
        book.pages = bookPages
        book.description = bookDesc
        addBookViewModel.currentBook.value = book
    }

}