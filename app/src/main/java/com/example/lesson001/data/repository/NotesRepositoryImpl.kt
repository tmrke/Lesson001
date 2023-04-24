package com.example.lesson001.data.repository

import com.example.lesson001.data.Note
import com.example.lesson001.data.NotesDataSource
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val notesDataSource: NotesDataSource = NotesDataSource
) : NotesRepository {
    override fun getNotes(): Flow<List<Note>> {
        return notesDataSource.getNotes()
    }

    override suspend fun addNote(text: String) {
        notesDataSource.addNote(text)
    }

    override suspend fun deleteNote(id: String) {
        notesDataSource.deleteNote(id)
    }
}