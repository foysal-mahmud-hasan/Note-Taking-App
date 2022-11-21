package com.foysal.practice.databasepractice.Repository

import androidx.annotation.WorkerThread
import com.foysal.practice.databasepractice.Model.Note
import com.foysal.practice.databasepractice.Room.NoteDAO
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao : NoteDAO) {

    val myAllNote : Flow<List<Note>> = noteDao.getAllNotes()

    @WorkerThread
    suspend fun insert(note: Note){

        noteDao.insert(note)

    }
    @WorkerThread
    suspend fun update(note: Note){

        noteDao.update(note)

    }
    @WorkerThread
    suspend fun delete(note: Note){

        noteDao.delete(note)

    }
    @WorkerThread
    suspend fun deleteAllNotes() : Unit = noteDao.deleteAllNotes()

}