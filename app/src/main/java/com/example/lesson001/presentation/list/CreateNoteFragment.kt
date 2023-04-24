package com.example.lesson001.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation


import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson001.R
import com.example.lesson001.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {
    private val binding by viewBinding(FragmentCreateNoteBinding::bind)
    private val viewModel by viewModels<NotesListViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        binding.floatingActionButton.setOnClickListener {
            val noteText = binding.editText.text.toString()

            if (noteText.isNotEmpty()) {
                viewModel.addNote(binding.editText.text.toString())
            }

            navController.navigate(R.id.notesListFragment)
        }
    }
}