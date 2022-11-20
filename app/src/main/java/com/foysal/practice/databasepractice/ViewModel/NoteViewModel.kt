package com.foysal.practice.databasepractice.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.foysal.practice.databasepractice.Model.Note
import com.foysal.practice.databasepractice.Repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel(){

    val myAllNotes : LiveData<List<Note>> = repository.myAllNote.asLiveData()

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO){



    }

}