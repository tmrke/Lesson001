package com.example.lesson001.domain

import com.example.lesson001.data.repository.NotesRepository
import com.example.lesson001.data.repository.NotesRepositoryImpl

class AddNoteUseCase(
    private val notesRepository : NotesRepository = NotesRepositoryImpl()
) {
    fun execute(text:String){
        notesRepository.addNote(text)
    }
}