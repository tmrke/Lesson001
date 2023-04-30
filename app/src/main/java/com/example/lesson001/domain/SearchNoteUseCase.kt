package com.example.lesson001.domain

import com.example.lesson001.data.Note
import com.example.lesson001.data.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    fun execute(text: String): Flow<List<Note>> {
        return notesRepository.getNotesBySearch(text)
    }
}