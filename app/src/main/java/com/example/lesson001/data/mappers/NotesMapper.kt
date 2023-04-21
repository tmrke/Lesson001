package com.example.lesson001.data.mappers

import androidx.room.Insert
import com.example.lesson001.data.Note
import com.example.lesson001.data.database.model.NoteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesMapper @Inject constructor() {
    fun fromEntityToUiModel(entity: NoteEntity): Note {
        return Note(
            id = entity.id,
            text = entity.text
        )
    }
}