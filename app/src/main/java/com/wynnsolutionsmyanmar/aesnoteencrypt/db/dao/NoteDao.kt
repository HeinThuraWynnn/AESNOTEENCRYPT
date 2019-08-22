package com.wynnsolutionsmyanmar.aesnoteencrypt.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wynnsolutionsmyanmar.aesnoteencrypt.db.entity.Note

@Dao
interface NoteDao {


    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM notes_table WHERE id = :id ")
    fun getById(id: String): LiveData<List<Note>>

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM notes_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM notes_table ")
    fun getAllNotes(): LiveData<List<Note>>

}