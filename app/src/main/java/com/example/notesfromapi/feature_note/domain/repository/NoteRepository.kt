package com.example.notesfromapi.feature_note.domain.repository

import com.example.notesfromapi.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun getAllNoteStream() : List<Note>

    val latestNotes: Flow<List<Note>>

}