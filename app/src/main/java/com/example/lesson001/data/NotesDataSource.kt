package com.example.lesson001.data

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.lesson001.NotesApplication

object NotesDataSource {

    val notesList = mutableListOf<Note>()


    fun addNote(text: String) {
        notesList.add(
            Note(
                text = text
            )
        )
    }
}