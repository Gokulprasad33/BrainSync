package com.example.brainsyncapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.BrainSync.screens.Note
import com.example.brainsyncapp.database.NoteDatabase
import com.example.brainsyncapp.database.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
//    private val settingsDoa = NoteDatabase.getDatabase(application).settingDoa()

//    val getTheme: Boolean= settingsDoa.theme
    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()
    val labelNotes: LiveData<List<NoteEntity>> =noteDao.getNoteByLabel()
    val priorityNotes: LiveData<List<NoteEntity>> =noteDao.getNoteByPriority()
    val getNoteById: LiveData<List<NoteEntity>> =noteDao.getNoteById()

    fun insertNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }

    fun getIdNote(noteId: Int): LiveData<Note> {
        return noteDao.getIdNote(noteId).map { noteEntity ->
            Note(
                title = noteEntity.noteTitle,
                content = noteEntity.noteContent
            )
        }
    }
}