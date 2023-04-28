package com.example.lesson001.presentation.creator

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson001.domain.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatorViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {
    fun addNote(text: String, bitmap: Bitmap?) {
        viewModelScope.launch {
            addNoteUseCase.execute(text, bitmap)
        }
    }
}