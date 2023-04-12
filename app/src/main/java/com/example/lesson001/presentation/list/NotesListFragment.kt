package com.example.lesson001.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson001.R
import com.example.lesson001.databinding.FragmentNotesListBinding

class NotesListFragment : Fragment(R.layout.fragment_notes_list) {

    companion object {
        private const val MOCK_TEXT = "Note text that resizes the card vertically to fit itself. It can be very long, but let’s settle on 180 characters as the limit"
    }

    private val binding by viewBinding(FragmentNotesListBinding::bind)
    private val viewModel by viewModels<NotesListViewModel>()

    private val listAdapter = NotesListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotes()

        with(binding) {
            recyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
                adapter = listAdapter.apply {
                    setCallback { note ->
                        Toast.makeText(requireContext(), note.text, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            floatingActionButton.setOnClickListener {
                viewModel.onAddClicked(MOCK_TEXT)
            }
        }


        viewModel.notesListLiveData.observe(viewLifecycleOwner){ list ->
            listAdapter.submitList(list)
        }
    }
}