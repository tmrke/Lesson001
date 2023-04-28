package com.example.lesson001.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson001.R
import com.example.lesson001.databinding.FragmentNotesListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private val binding by viewBinding(FragmentNotesListBinding::bind)
    private val viewModel by viewModels<NotesListViewModel>()

    @Inject
    lateinit var listAdapter: NotesListAdapter      //lateinit на случай, если listAdapter будет null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        viewModel.getNotes()

        val recyclerView = binding.recyclerView

        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

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

        binding.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.createNoteFragment)
        }

        binding.ImageButtonSearch.setOnClickListener {
            navController.navigate(R.id.searchNoteFragment)
        }

        viewModel.notesListLiveData.observe(viewLifecycleOwner) { list ->
            listAdapter.submitList(list)
        }
    }
    //viewLifecycleOwner - жизненный цикл View.  гарантирует, что LiveData будет наблюдаться только тогда, когда View фрагмента существует.
}