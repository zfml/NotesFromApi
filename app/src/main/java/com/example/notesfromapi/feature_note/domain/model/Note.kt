package com.example.notesfromapi.feature_note.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Note(
    val id: Int,
    val title: String,
    val completed: Boolean
)