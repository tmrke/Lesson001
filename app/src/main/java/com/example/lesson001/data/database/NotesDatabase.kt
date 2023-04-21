package com.example.lesson001.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lesson001.data.database.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDAO(): NotesDAO
}