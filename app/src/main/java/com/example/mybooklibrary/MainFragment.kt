package com.example.mybooklibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.mybooklibrary.databinding.FragmentMainBinding


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )

        binding.btnAllBooks.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAllBooksFragment(ALL_BOOK_KEY))
        }

        binding.btnFavBooks.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAllBooksFragment(FAVOURITE_BOOK_KEY))
        }

        binding.btnReadBooks.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAllBooksFragment(ALREADY_READ_BOOK_KEY))
        }

        binding.btnAddBook.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddBookFragment())
        }

        return binding.root
    }


}