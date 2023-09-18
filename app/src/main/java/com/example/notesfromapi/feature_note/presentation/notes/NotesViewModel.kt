package com.example.notesfromapi.feature_note.presentation.notes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesfromapi.feature_note.domain.model.Note
import com.example.notesfromapi.feature_note.domain.use_case.GetAllNotesUseCase
import com.example.notesfromapi.feature_note.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<NotesUIState> = MutableStateFlow(NotesUIState.Success(emptyList()))

    val uiState: StateFlow<NotesUIState> = _uiState.asStateFlow()

    init {
        getAllNotes()
    }

    fun getAllNotes() {

        _uiState.value = NotesUIState.Loading


        try {
            viewModelScope.launch(Dispatchers.IO){
                noteUseCase.getAllNotesUseCase()
                    .catch {
                        _uiState.value = NotesUIState.Error
                    }
                    .collect{
                        _uiState.value = NotesUIState.Success(it)
                    }

            }
        }catch (e: Exception) {
            _uiState.value = NotesUIState.Error
        }catch (e: HttpException) {
            _uiState.value = NotesUIState.Error
        }

    }


}

sealed interface NotesUIState{
    object Loading: NotesUIState
    data class Success(val list: List<Note>): NotesUIState
    object Error: NotesUIState
}