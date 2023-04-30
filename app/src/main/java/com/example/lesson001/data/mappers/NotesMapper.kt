package com.example.lesson001.data.mappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.lesson001.data.Note
import com.example.lesson001.data.database.model.NoteEntity
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesMapper @Inject constructor() {
    fun toUiModel(entity: NoteEntity): Note {
        return Note(
            id = entity.id,
            text = entity.text,
            bitmap = if (entity.imageData != null) {
                toBitmap(entity.imageData)
            } else {
                null
            }
        )
    }

    fun toEntity(note: Note): NoteEntity {
        return NoteEntity(
            id = note.id,
            text = note.text,
            imageData = if (note.bitmap != null) {
                toByteArray(note.bitmap)
            } else {
                null
            }
        )
    }

    private fun toBitmap(byteArray: ByteArray?): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray?.size ?: 0) //TODO поиграться со сжатием изображением
    }

    fun toByteArray(bitmap: Bitmap?): ByteArray? {
       return if( bitmap != null){
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 1, outputStream)//TODO поиграться со сжатием изображением
            return outputStream.toByteArray()
        } else {
            null
        }
    }
}