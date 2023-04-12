package com.example.lesson001.data.repository

import com.example.lesson001.data.Note


interface NotesRepository {
    fun getNotes() : List<Note>
    fun addNote(text: String)
}