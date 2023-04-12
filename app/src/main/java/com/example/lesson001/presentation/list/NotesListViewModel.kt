package com.example.lesson001.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson001.data.Note
import com.example.lesson001.domain.AddNoteUseCase
import com.example.lesson001.domain.GetNotesUseCase

class NotesListViewModel(
    private val getNotesUseCase: GetNotesUseCase = GetNotesUseCase(),
    private val addNoteUseCase: AddNoteUseCase = AddNoteUseCase()
) : ViewModel() {

    private val _notesListLiveData = MutableLiveData<List<Note>>()
    val notesListLiveData: LiveData<List<Note>> = _notesListLiveData

    fun onAddClicked(text:String) {
        addNoteUseCase.execute(text)
        getNotes()
    }

    fun getNotes() {
        _notesListLiveData.value = getNotesUseCase.execute().toList()
    }
}


fun MutableLiveData<*>.toLiveData() = this as LiveData<*>