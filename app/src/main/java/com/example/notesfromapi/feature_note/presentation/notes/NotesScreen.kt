@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.notesfromapi.feature_note.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notesfromapi.feature_note.domain.model.Note
import com.example.notesfromapi.feature_note.presentation.components.NoteItem

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Notes")
                }
            )
        },
        content = { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding)) {

                when(uiState.value) {
                    NotesUIState.Error -> ErrorScreen(onRetryClicked = viewModel::getAllNotes)
                    NotesUIState.Loading -> LoadingScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                    is NotesUIState.Success -> NotesContent(notes = (uiState.value as NotesUIState.Success).list)
                }


            }

        }
    )

}


@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        CircularProgressIndicator()
    }

}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading Error")

        Button(onClick = onRetryClicked) {
            Text(text = "Retry")
        }
    }
}


@Composable
fun NotesContent(
    modifier: Modifier = Modifier,
    notes: List<Note>
) {
    LazyColumn(modifier = modifier) {
        items(notes, key = {note -> note.id}) { note ->
            NoteItem(note = note)
        }
    }
}