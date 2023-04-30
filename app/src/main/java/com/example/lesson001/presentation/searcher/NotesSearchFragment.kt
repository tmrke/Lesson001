package com.example.lesson001.presentation.searcher

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson001.R
import com.example.lesson001.databinding.FragmentSearchBinding
import com.example.lesson001.presentation.NotesListAdapter
import com.example.lesson001.presentation.list.SwipeToDeleteCallback

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesSearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel by viewModels<SearcherViewModel>()

    @Inject
    lateinit var listAdapter: NotesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        binding.toolbar.setOnClickListener {
            navController.navigate(R.id.notesListFragment)
        }

        val searchHandler = Handler(Looper.getMainLooper())
        val searchRunnable = Runnable {

            viewModel.getNotesBySearch(binding.editTextSearchField.text.toString())
        }

        binding.editTextSearchField.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchHandler.removeCallbacks(searchRunnable)
                searchHandler.postDelayed(searchRunnable, 500)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        val recyclerView = binding.recyclerView

        viewModel.notesListLiveData.observe(viewLifecycleOwner) { notes ->
            listAdapter.submitList(notes)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)

            adapter = listAdapter.apply {
                setCallbackSwipeToDelete { note ->
                    viewModel.deleteNote(note.id)
                }
            }
        }

        val swipeToDeleteCallback =
            SwipeToDeleteCallback(binding.recyclerView.adapter as NotesListAdapter)

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}