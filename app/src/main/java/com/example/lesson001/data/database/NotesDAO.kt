package com.example.lesson001.data.database

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lesson001.data.database.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Insert
    suspend fun addNote(note: NoteEntity): Long

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long)

}