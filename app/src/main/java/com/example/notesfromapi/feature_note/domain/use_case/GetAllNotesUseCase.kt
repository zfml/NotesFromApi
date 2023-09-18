package com.example.notesfromapi.feature_note.domain.use_case

import com.example.notesfromapi.feature_note.domain.model.Note
import com.example.notesfromapi.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetAllNotesUseCase (
    private val noteRepository: NoteRepository
){
    operator fun invoke(): Flow<List<Note>> {
       return noteRepository.latestNotes
    }
}