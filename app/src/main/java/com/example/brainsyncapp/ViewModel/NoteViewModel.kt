package com.example.brainsyncapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.brainsyncapp.database.NoteDatabase
import com.example.brainsyncapp.database.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()

    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()

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
}