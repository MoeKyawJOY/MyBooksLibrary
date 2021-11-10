package com.example.mybooklibrary.bookdetails

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBinderMapper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mybooklibrary.R
import com.example.mybooklibrary.database.BookDatabase
import com.example.mybooklibrary.databinding.FragmentBookDetailsBinding


class BookDetailsFragment : Fragment() {

    private val safeArgs: BookDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentBookDetailsBinding>(
            inflater, R.layout.fragment_book_details, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao

        val bookDetailsViewModelFactory = BookDetailsViewModelFactory(dataSource, application, safeArgs.book)
        val bookDetailsViewModel = ViewModelProvider(this, bookDetailsViewModelFactory).get(BookDetailsViewModel::class.java)

        binding.bookViewModel = bookDetailsViewModel
        binding.lifecycleOwner = this

        bookDetailsViewModel.navigateToAllBooks.observe(viewLifecycleOwner, Observer {  toNavigate ->
            if(toNavigate){
                findNavController().navigate(BookDetailsFragmentDirections.actionBookDetailsFragmentToAllBooksFragment(safeArgs.key))
                bookDetailsViewModel.onNavigateComplete()
            }
        })

        binding.btnUpdate.setOnClickListener {
            findNavController().navigate(BookDetailsFragmentDirections.actionBookDetailsFragmentToUpdateBookFragment(bookDetailsViewModel.book, safeArgs.key))
        }

        binding.btnDelete.setOnClickListener {
            showAlertDialog(bookDetailsViewModel)
        }

        return binding.root
    }

    fun showAlertDialog(bookDetailsViewModel: BookDetailsViewModel){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Do you want to delete this book?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                bookDetailsViewModel.onDeleteClicked()
                Toast.makeText(context, "Book Deleted Successfully", Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            })

        val alertDialog = builder.create()
        alertDialog.setTitle("Delete Warning")
        alertDialog.show()

    }


}