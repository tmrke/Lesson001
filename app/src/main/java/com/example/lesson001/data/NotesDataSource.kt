package com.example.lesson001.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.lesson001.NotesApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map


object NotesDataSource {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "notes")

    private val applicationContext: Context? get() = NotesApplication.getApplicationContext()

    suspend fun addNote(text: String) {
        applicationContext?.let { context ->
            context.dataStore.edit { preferences ->
                val note = Note(text = text)
                val key = stringPreferencesKey(note.id)
                preferences[key] = note.text
            }
        }
    }

    fun getNotes(): Flow<List<Note>> {
        return applicationContext?.let { context ->
            context.dataStore.data.map { preferences ->
                preferences.asMap().map { entry ->
                    Note(
                        id = entry.key.name,
                        text = entry.value.toString()
                    )
                }
            }
        } ?: emptyFlow()
    }
}