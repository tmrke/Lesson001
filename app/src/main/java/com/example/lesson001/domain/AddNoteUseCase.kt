package com.example.lesson001.domain

import com.example.lesson001.data.repository.NotesRepository
import com.example.lesson001.data.repository.NotesRepositoryImpl
import javax.inject.Inject

class AddNoteUseCase  @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend fun execute(text: String) {
        notesRepository.addNote(text)
    }
}