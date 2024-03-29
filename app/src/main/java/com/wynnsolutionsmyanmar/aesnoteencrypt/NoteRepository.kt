package com.wynnsolutionsmyanmar.aesnoteencrypt

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.wynnsolutionsmyanmar.aesnoteencrypt.db.NoteDatabase
import com.wynnsolutionsmyanmar.aesnoteencrypt.db.dao.NoteDao
import com.wynnsolutionsmyanmar.aesnoteencrypt.db.entity.Note

class NoteRepository(application: Application) {

    private var noteDao: NoteDao

    private var allNotes: LiveData<List<Note>>

    init {
        val database: NoteDatabase = NoteDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(noteDao).execute(note)
    }
    fun getById(note: Note){
        val getByIdNoteAsyncTask  = GetByIdNoteAsyncTask(noteDao).execute(note)
    }


    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            noteDao
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
        val noteDao = noteDao

        override fun doInBackground(vararg p0: Note?) {
            noteDao.insert(p0[0]!!)
        }
    }


    private  class GetByIdNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note,Unit, Unit>(){
    val noteDao = noteDao
        override  fun doInBackground(vararg p0: Note?){
            noteDao.getById(p0['id'])
        }
    }
    private class DeleteAllNotesAsyncTask(val noteDao: NoteDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            noteDao.deleteAllNotes()
        }
    }

}