package com.example.lesson001.domain

import com.example.lesson001.data.repository.NotesRepository
import javax.inject.Inject

class SearchNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    fun execute(text: String) {
        notesRepository.getNotesBySearch(text)
    }
}