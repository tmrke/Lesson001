package com.example.lesson001.presentation.creator

import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation


import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson001.R
import com.example.lesson001.databinding.FragmentCreateNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {
    private val binding by viewBinding(FragmentCreateNoteBinding::bind)
    private val viewModel by viewModels<CreatorViewModel>()
    private var bitmap: Bitmap? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        with(binding) {
            val pickMedia =
                registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    uri?.let { imageUri ->
                        bitmap = MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver,
                            imageUri
                        )

                        imageView.setImageBitmap(bitmap)
                        imageView.visibility = View.VISIBLE
                    }
                }

            imageButtonAddImage.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                imageButtonAddImage.visibility = View.INVISIBLE
            }

            floatingActionButton.setOnClickListener {
                val text = binding.editText.text.toString()

                if (text.isEmpty() && bitmap == null) {
                    Toast.makeText(context, "Note can't be empty", Toast.LENGTH_LONG).show()
                } else {
                    viewModel.addNote(text, bitmap)
                    navController.navigate(R.id.notesListFragment)
                }
            }

            toolbar.setOnClickListener {
                navController.navigate(R.id.notesListFragment)
            }
        }
    }
}