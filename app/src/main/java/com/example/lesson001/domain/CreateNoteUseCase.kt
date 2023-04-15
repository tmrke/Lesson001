package com.example.lesson001.domain

import com.example.lesson001.data.repository.NotesRepository
import com.example.lesson001.data.repository.NotesRepositoryImpl

class CreateNoteUseCase(
    private val notesRepository: NotesRepository = NotesRepositoryImpl()
) {
    suspend fun execute(text: String){
        notesRepository.createNote(text)
    }
}