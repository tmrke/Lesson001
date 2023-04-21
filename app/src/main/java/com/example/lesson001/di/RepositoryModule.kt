package com.example.lesson001.di

import com.example.lesson001.data.repository.NotesRepository
import com.example.lesson001.data.repository.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class) //показываем куда установаить зависимости
abstract class RepositoryModule {
    @Binds
    abstract fun bindNotesRepository(impl: NotesRepositoryImpl): NotesRepository
}