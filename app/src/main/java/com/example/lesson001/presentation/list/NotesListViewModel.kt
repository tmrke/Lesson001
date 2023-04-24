package com.example.lesson001.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson001.data.Note
import com.example.lesson001.domain.AddNoteUseCase
import com.example.lesson001.domain.DeleteNotesUseCase
import com.example.lesson001.domain.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNotesUseCase
) : ViewModel() {
    private val _notesListLiveData = MutableLiveData<List<Note>>()
    val notesListLiveData: LiveData<List<Note>> = _notesListLiveData

        fun getNotes() {
        viewModelScope.launch {
            getNotesUseCase.execute().collect { list ->
                _notesListLiveData.value = list
            }
        }
    }

    fun addNote(text: String) {
        viewModelScope.launch {
            addNoteUseCase.execute(text)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            deleteNoteUseCase.execute(id)
            getNotes()
        }
    }
}

fun MutableLiveData<*>.toLiveData() = this as LiveData<*>