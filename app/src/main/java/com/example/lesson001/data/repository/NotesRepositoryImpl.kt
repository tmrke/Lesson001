package com.example.lesson001.data.repository

import android.provider.ContactsContract
import com.example.lesson001.data.Note
import com.example.lesson001.data.NotesDataSource

class NotesRepositoryImpl(
    private val notesDataSource: NotesDataSource = NotesDataSource
) : NotesRepository {
    override fun getNotes(): List<Note> {
        return notesDataSource.notesList
    }

    override fun addNote(text: String) {
        notesDataSource.addNote(text)
    }
}