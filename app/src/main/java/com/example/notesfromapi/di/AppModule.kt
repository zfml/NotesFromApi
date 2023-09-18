package com.example.notesfromapi.di

import com.example.notesfromapi.feature_note.data.data_source.network.NoteApi
import com.example.notesfromapi.feature_note.data.repository.NoteRepositoryImpl
import com.example.notesfromapi.feature_note.data.util.Constants.BASE_URL
import com.example.notesfromapi.feature_note.domain.repository.NoteRepository
import com.example.notesfromapi.feature_note.domain.use_case.GetAllNotesUseCase
import com.example.notesfromapi.feature_note.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteApi(retrofit: Retrofit): NoteApi {
        return retrofit.create(NoteApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNoteRepository(noteApi: NoteApi): NoteRepository {
        return NoteRepositoryImpl(noteApi)
    }

    @Singleton
    @Provides
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getAllNotesUseCase = GetAllNotesUseCase(repository)
        )
    }

}