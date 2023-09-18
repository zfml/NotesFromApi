package com.example.notesfromapi.feature_note.data.repository

import com.example.notesfromapi.feature_note.data.data_source.network.NoteApi
import com.example.notesfromapi.feature_note.domain.model.Note
import com.example.notesfromapi.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NoteRepositoryImpl(
    private val noteApi: NoteApi
): NoteRepository {
    override suspend fun getAllNoteStream(): List<Note> {
        return noteApi.getAllNotes()
    }

    override val latestNotes: Flow<List<Note>> = flow {
        while (true) {
            val allNotes = noteApi.getAllNotes()
            emit(allNotes)
        }
    }

}