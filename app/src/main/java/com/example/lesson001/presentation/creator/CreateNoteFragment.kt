package com.example.lesson001.presentation.creator

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation


import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson001.R
import com.example.lesson001.databinding.FragmentCreateNoteBinding
import com.example.lesson001.presentation.list.NotesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {
    private val binding by viewBinding(FragmentCreateNoteBinding::bind)
    private val viewModel by viewModels<NotesListViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        with(binding) {
            floatingActionButton.setOnClickListener {
                viewModel.addNote(binding.editText.text.toString())
                navController.navigate(R.id.notesListFragment)
            }

            val getContent =
                registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                    uri?.let { imageUri ->
                        imageView.setImageURI(imageUri)
                        imageView.visibility = View.VISIBLE
                    }
                }

            imageButtonAddImage.setOnClickListener {
                getContent.launch("image/*")
                imageButtonAddImage.visibility = View.INVISIBLE
            }
        }
    }
}