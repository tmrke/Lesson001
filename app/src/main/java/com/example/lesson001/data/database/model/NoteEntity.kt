package com.example.lesson001.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,      //автогенерация работает только при стартовом значении = 0
    val text: String

)