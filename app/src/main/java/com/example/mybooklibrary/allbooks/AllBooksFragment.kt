package com.example.mybooklibrary.allbooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mybooklibrary.R
import com.example.mybooklibrary.database.BookDatabase
import com.example.mybooklibrary.databinding.FragmentAllBooksBinding


class AllBooksFragment : Fragment() {

    private lateinit var allBooksViewModel: AllBooksViewModel
    private lateinit var allBooksViewModelFactory: AllBooksViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAllBooksBinding>(
            inflater, R.layout.fragment_all_books, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao

        allBooksViewModelFactory = AllBooksViewModelFactory(dataSource, application)
        allBooksViewModel = ViewModelProvider(this, allBooksViewModelFactory).get(AllBooksViewModel::class.java)



        return binding.root
    }

}