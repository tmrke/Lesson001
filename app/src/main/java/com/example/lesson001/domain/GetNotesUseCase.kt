package com.example.lesson001.domain

import com.example.lesson001.data.Note
import com.example.lesson001.data.repository.NotesRepository
import com.example.lesson001.data.repository.NotesRepositoryImpl

class GetNotesUseCase(
    private val notesRepository: NotesRepository = NotesRepositoryImpl()
) {
    fun execute(): List<Note> {
        return notesRepository.getNotes()
    }
}