package com.example.lesson001.presentation.searcher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson001.R
import com.example.lesson001.databinding.FragmentNotesListBinding
import com.example.lesson001.databinding.FragmentSearchBinding
import com.example.lesson001.presentation.list.NotesListAdapter
import com.example.lesson001.presentation.list.NotesListViewModel
import com.example.lesson001.presentation.list.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesSearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel by viewModels<SearcherViewModel>()

    @Inject
    lateinit var listAdapter: ListBySearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        binding.imageButtonBack.setOnClickListener {
            navController.navigate(R.id.notesListFragment)
        }

        viewModel.getNotesBySearch(binding.editTextSearchField.text.toString())

        val recyclerView = binding.recyclerView

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

//        val swipeToDeleteCallback =
//            SwipeToDeleteCallback(binding.recyclerView.adapter as NotesListAdapter)
//
//        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
//        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}