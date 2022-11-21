package com.foysal.practice.databasepractice.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foysal.practice.databasepractice.Adapters.NoteAdapter
import com.foysal.practice.databasepractice.Model.Note
import com.foysal.practice.databasepractice.NoteApplication
import com.foysal.practice.databasepractice.R
import com.foysal.practice.databasepractice.ViewModel.NoteViewModel
import com.foysal.practice.databasepractice.ViewModel.NoteViewModelFactory

lateinit var noteViewModel : NoteViewModel

class MainActivity : AppCompatActivity() {

    lateinit var addActivityResultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val noteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter

        //REGISTER ACTIVITY FOR RESULT
        registerActivityResultLauncher()


        noteViewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        noteViewModel.myAllNotes.observe(this, Observer {
            notes ->
            noteAdapter.setNote(notes)
        })

    }

    fun registerActivityResultLauncher(){

        addActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()
            , ActivityResultCallback { resultAddNote ->

                val resultCode = resultAddNote.resultCode
                val data = resultAddNote.data

                if ( resultCode == RESULT_OK && data != null){

                    val noteTitle : String = data.getStringExtra("title").toString()
                    val noteDescription : String = data.getStringExtra("description").toString()

                    val note = Note(noteTitle, noteDescription)
                    noteViewModel.insert(note)

                }

            })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_add_note->{
                val intent = Intent(this, NoteAddActivity::class.java)
                addActivityResultLauncher.launch(intent)
            }
            R.id.item_delete_all_note -> Toast.makeText(applicationContext,
            "Delete icon was clicked", Toast.LENGTH_SHORT).show()
        }
        return true
    }


}