package com.foysal.practice.databasepractice

import android.app.Application
import com.foysal.practice.databasepractice.Repository.NoteRepository
import com.foysal.practice.databasepractice.Room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }

}