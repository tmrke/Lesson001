package com.example.lesson001.presentation.searcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    fun getNotesBySearch(text: String) {
        viewModelScope.launch {
            searchNotesUseCase.execute(text)
        }
    }
}