package com.foysal.practice.databasepractice.ViewModel

import androidx.lifecycle.*
import com.foysal.practice.databasepractice.Model.Note
import com.foysal.practice.databasepractice.Repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel(){

    val myAllNotes : LiveData<List<Note>> = repository.myAllNote.asLiveData()

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO){

        repository.insert(note)

    }
    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO){

        repository.update(note)

    }
    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO){

        repository.delete(note)

    }
    fun deleteAllNotes(note: Note) = viewModelScope.launch(Dispatchers.IO){

        repository.deleteAllNotes()

    }

}

class NoteViewModelFactory(private var repository: NoteRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }else{
            throw java.lang.IllegalArgumentException("Unknown View Model")
        }
    }

}