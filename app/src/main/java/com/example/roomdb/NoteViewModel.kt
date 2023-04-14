package com.example.roomdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>

    val repository : NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()

        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNode (note: Note) = viewModelScope.launch( Dispatchers.IO )
    {
        repository.delete(note)
    }

    fun updateNode (note: Note) = viewModelScope.launch( Dispatchers.IO )
    {
        repository.update(note)
    }

    fun AddNode (note: Note) = viewModelScope.launch( Dispatchers.IO )
    {
        repository.insert(note)
    }

}
