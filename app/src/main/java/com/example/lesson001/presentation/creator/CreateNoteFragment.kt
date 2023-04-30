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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation


import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson001.R
import com.example.lesson001.databinding.FragmentCreateNoteBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {
    private val binding by viewBinding(FragmentCreateNoteBinding::bind)
    private val viewModel by viewModels<CreatorViewModel>()
    private var bitmap: Bitmap? = null

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                lifecycleScope.launch {
                    bitmap = withContext(Dispatchers.IO) {
                        Picasso.get().load(uri).resize(500, 0).centerCrop().get()
                    }

                    binding.imageView.setImageBitmap(bitmap)
                    binding.imageView.visibility = View.VISIBLE
                }
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        with(binding) {
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