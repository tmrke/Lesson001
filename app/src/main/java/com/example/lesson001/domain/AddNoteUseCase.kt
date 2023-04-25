package com.example.lesson001.domain

import android.graphics.Bitmap
import com.example.lesson001.data.repository.NotesRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend fun execute(text: String, bitmap: Bitmap?) {
        notesRepository.addNote(text, bitmap)
    }
}