package com.example.lesson001.domain

import com.example.lesson001.data.repository.NotesRepository
import com.example.lesson001.data.repository.NotesRepositoryImpl
import javax.inject.Inject

class DeleteNotesUseCase  @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend fun execute(id: Long){
        notesRepository.deleteNote(id)
    }
}