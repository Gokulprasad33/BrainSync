package com.example.brainsyncapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val noteTitle: String,
    val noteContent: String,
    val noteDueDate: String? = null,
    val notePriority: String? = "Normal",
    val noteLabel: String? = "None"
)