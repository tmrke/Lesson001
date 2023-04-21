package com.example.lesson001.di

import android.content.Context
import androidx.room.Room
import com.example.lesson001.data.database.NotesDAO
import com.example.lesson001.data.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)       //определяет типа область действия этих инъекций, куда устанавливаем зависимости
object DatabaseModule {
    private const val DB_NAME = "notes_db"

    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            DB_NAME
        ).build()       //создаем бд
    }

    @Provides       //предоставляет этот метод
    @Singleton      // создается только 1 раз
    fun provideNotesDAO(db: NotesDatabase): NotesDAO {
        return db.notesDAO()
    }
}