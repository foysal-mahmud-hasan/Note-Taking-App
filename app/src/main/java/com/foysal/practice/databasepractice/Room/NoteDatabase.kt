package com.foysal.practice.databasepractice.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.foysal.practice.databasepractice.Model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.security.AccessControlContext

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDAO

    //Singleton

    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase?= null

        fun getDatabase(context: Context, scope: CoroutineScope) : NoteDatabase {

            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(context.applicationContext,
                NoteDatabase::class.java, "note_database")
                    .addCallback(NoteDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance

            }
        }

    }

    private class NoteDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->

                //database.getNoteDao().insert(Note("t","d"))

                scope.launch {

                    val noteDAO = database.getNoteDao()

                    noteDAO.insert(Note("Title 1", "Description 1"))
                    noteDAO.insert(Note("Title 2", "Description 2"))
                    noteDAO.insert(Note("Title 3", "Description 3"))

                }

            }

        }

    }

}