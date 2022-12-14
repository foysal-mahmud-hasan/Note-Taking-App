package com.foysal.practice.databasepractice.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.foysal.practice.databasepractice.R

class NoteAddActivity : AppCompatActivity() {

    lateinit var editTextTitle : EditText
    lateinit var editTextDescription : EditText
    lateinit var buttonCancel : Button
    lateinit var buttonSave : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add)
        // Action bar title for Activity
        supportActionBar?.title = "Add Note"

        editTextTitle = findViewById(R.id.editTextNoteTitle)
        editTextDescription = findViewById(R.id.editTextNoteDescription)
        buttonCancel = findViewById(R.id.buttonCancel)
        buttonSave = findViewById(R.id.buttonSave)

        buttonCancel.setOnClickListener{

            Toast.makeText(applicationContext, "Nothing Saved", Toast.LENGTH_SHORT).show()
            finish()

        }
        buttonSave.setOnClickListener{
            if(editTextTitle.text.toString() != "" && editTextDescription.text.toString() != ""){
                saveNote()
            }else{
                Toast.makeText(applicationContext, "No Entries Cant Be Empty", Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun saveNote(){

        val noteTitle : String = editTextTitle.text.toString()
        val noteDescription : String = editTextDescription.text.toString()

        val intent = Intent()
        intent.putExtra("title", noteTitle)
        intent.putExtra("description", noteDescription)
        setResult(RESULT_OK,intent)
        finish()
    }
}