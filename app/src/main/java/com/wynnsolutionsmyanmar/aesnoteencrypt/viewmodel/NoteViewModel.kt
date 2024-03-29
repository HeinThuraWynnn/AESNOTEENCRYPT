package com.wynnsolutionsmyanmar.aesnoteencrypt.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wynnsolutionsmyanmar.aesnoteencrypt.NoteRepository
import com.wynnsolutionsmyanmar.aesnoteencrypt.db.entity.Note

class NoteViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: NoteRepository =
        NoteRepository(application)
    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

    fun insert(note: Note) {
        repository.insert(note)
    }
    fun getById(note: Note){
        repository.getById(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}
