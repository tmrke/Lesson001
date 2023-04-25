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
    fun fromEntityToUiModel(entity: NoteEntity): Note {
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

    private fun toBitmap(byteArray: ByteArray?): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray?.size ?: 0)
    }

    fun toByteArray(bitmap: Bitmap?): ByteArray? {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

        return outputStream.toByteArray()
    }
}