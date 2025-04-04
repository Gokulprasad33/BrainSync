package com.example.brainsyncapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes ORDER BY noteLabel")
    fun getNoteByLabel(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes ORDER BY notePriority")
    fun getNoteByPriority(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes ORDER BY id")
    fun getNoteById(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id=:noteId LIMIT 1")
    fun getIdNote(noteId: Int): LiveData<NoteEntity>
}