package com.example.mybooklibrary.updatebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mybooklibrary.R
import com.example.mybooklibrary.database.BookDatabase
import com.example.mybooklibrary.databinding.FragmentUpdateBookBinding


class UpdateBookFragment : Fragment() {

    val args: UpdateBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUpdateBookBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val database = BookDatabase.getInstance(application).bookDatabaseDao

        val viewModelFactory = UpdateBookViewModelFactory(database, application, args.book)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(UpdateBookViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnUpdate2.setOnClickListener {
            val book = viewModel.book
            viewModel.book.title = binding.edtTitle2.text.toString()
            viewModel.book.author = binding.edtAuthor2.text.toString()
            viewModel.book.pages = binding.edtPages2.text.toString().toInt()
            viewModel.book.description = binding.edtDescriptions2.text.toString()
            viewModel.book.isFavourite = binding.swthFavourite.isChecked
            viewModel.book.hasRead = binding.swthAlreadyRead.isChecked
            viewModel.onUpdateClicked()
        }

        viewModel.navigateToAllBooks.observe(viewLifecycleOwner, Observer{ toNavigate ->
            if(toNavigate){
                findNavController().navigate(UpdateBookFragmentDirections.actionUpdateBookFragmentToAllBooksFragment(args.key))
                viewModel.onNavigateComplete()
            }
        })

        return binding.root
    }


}