package com.example.lesson001.data.repository

import android.graphics.Bitmap
import com.example.lesson001.data.Note
import kotlinx.coroutines.flow.Flow


interface NotesRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(text: String, bitmap: Bitmap?)
    suspend fun deleteNote(id: Long)
    fun getNotesBySearch(text: String): Flow<List<Note>>
}