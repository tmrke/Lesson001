package com.example.lesson001.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,      //автогенерация работает только при стартовом значении = 0
    val text: String,
    val imageData: ByteArray?

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NoteEntity

        if (id != other.id) return false
        if (text != other.text) return false
        if (imageData != null) {
            if (other.imageData == null) return false
            if (!imageData.contentEquals(other.imageData)) return false
        } else if (other.imageData != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + (imageData?.contentHashCode() ?: 0)
        return result
    }
}