package com.example.mybooklibrary.allbooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mybooklibrary.BookListAdapter
import com.example.mybooklibrary.R
import com.example.mybooklibrary.database.BookDatabase
import com.example.mybooklibrary.databinding.FragmentAllBooksBinding


class AllBooksFragment : Fragment() {

    private lateinit var allBooksViewModel: AllBooksViewModel
    private lateinit var allBooksViewModelFactory: AllBooksViewModelFactory

    private val args: AllBooksFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAllBooksBinding>(
            inflater, R.layout.fragment_all_books, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao

        allBooksViewModelFactory = AllBooksViewModelFactory(dataSource, application, args.key)
        allBooksViewModel = ViewModelProvider(this, allBooksViewModelFactory).get(AllBooksViewModel::class.java)

        binding.allBookViewModel = allBooksViewModel
        binding.lifecycleOwner = this

        val bookAdapter = BookListAdapter(BookListAdapter.BookClickListener { book ->
            findNavController().navigate(AllBooksFragmentDirections.actionAllBooksFragmentToBookDetailsFragment(book,args.key))
        })

        binding.bookRecyclerView.adapter = bookAdapter

        allBooksViewModel.allBooks.observe(viewLifecycleOwner, Observer{
            it?.let {
                bookAdapter.submitList(it)
            }
        })



        return binding.root
    }

}