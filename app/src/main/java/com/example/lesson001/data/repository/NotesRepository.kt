package com.example.lesson001.data.repository

import com.example.lesson001.data.Note
import kotlinx.coroutines.flow.Flow


interface NotesRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(text: String)
    suspend fun createNote(text: String)
    suspend fun deleteNote(id: Long)
}