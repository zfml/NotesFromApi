package com.example.notesfromapi.feature_note.presentation.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.notesfromapi.feature_note.domain.model.Note

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(64.dp)
        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ){
        Column (
            modifier = Modifier.padding(8.dp)
         ){
            Text(
                text = note.title ,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }

}