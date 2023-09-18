package com.example.notesfromapi.feature_note.data.data_source.network

import com.example.notesfromapi.feature_note.data.util.Constants.END_POINT
import com.example.notesfromapi.feature_note.domain.model.Note
import retrofit2.http.GET


interface NoteApi {

    @GET(END_POINT)
    suspend fun getAllNotes(): List<Note>

}