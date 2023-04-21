package com.example.lesson001.data.repository

import com.example.lesson001.data.Note
import com.example.lesson001.data.database.NotesDAO
import com.example.lesson001.data.database.model.NoteEntity
import com.example.lesson001.data.mappers.NotesMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesMapper: NotesMapper,
    private val notesDAO: NotesDAO
) : NotesRepository {
    override fun getNotes(): Flow<List<Note>> {
        return notesDAO.getNotes().map { list ->
            list.map { note -> notesMapper.fromEntityToUiModel(note) }
        }
    }

    override suspend fun addNote(text: String) {
        notesDAO.addNote(NoteEntity(text = text))
    }

    override suspend fun createNote(text: String) {
//        notesDAO.createNote(text)
    }

    override suspend fun deleteNote(id: Long) {
//        notesDAO.deleteNote(id)
    }
}