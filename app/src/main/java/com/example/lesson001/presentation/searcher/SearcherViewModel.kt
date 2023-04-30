package com.example.lesson001.presentation.searcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson001.data.Note
import com.example.lesson001.domain.DeleteNotesUseCase
import com.example.lesson001.domain.SearchNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearcherViewModel @Inject constructor(
    private val searchNotesUseCase: SearchNoteUseCase,
    private val deleteNotesUseCase: DeleteNotesUseCase
) : ViewModel() {
    private val _notesListLiveData = MutableLiveData<List<Note>>()
    val notesListLiveData: LiveData<List<Note>> = _notesListLiveData
    fun deleteNote(id: Long) {
        viewModelScope.launch {
            deleteNotesUseCase.execute(id)
        }
    }

    fun getNotesBySearch(text: String) {
        viewModelScope.launch {
            if (text.isEmpty()) {
                _notesListLiveData.value = emptyList()
            } else {
                searchNotesUseCase.execute(text).collect { list ->
                    _notesListLiveData.value = list
                }
            }
        }
    }
}