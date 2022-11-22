package com.foysal.practice.databasepractice.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.foysal.practice.databasepractice.R

class EditNoteActivity : AppCompatActivity() {

    lateinit var editTextTitle : EditText
    lateinit var editTextDescription :  EditText
    lateinit var buttonCancel : Button
    lateinit var buttonSave : Button

    var currentId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        supportActionBar?.title = "Edit Note"

        editTextTitle = findViewById(R.id.editTextEditNoteTitle)
        editTextDescription = findViewById(R.id.editTextEditNoteDescription)
        buttonCancel = findViewById(R.id.EditNoteBtnCancel)
        buttonSave = findViewById(R.id.EditNoteBtnSave)

        getAndSetData()

        buttonCancel.setOnClickListener{

            Toast.makeText(applicationContext, "Nothing Edited", Toast.LENGTH_SHORT).show()
            finish()

        }
        buttonSave.setOnClickListener {
            editNote()
        }
    }
    fun editNote(){

        val editedTitle = editTextTitle.text.toString()
        val editedDescription = editTextDescription.text.toString()

        val intent = Intent()
        intent.putExtra("editedTitle", editedTitle)
        intent.putExtra("editedDescription", editedDescription)
        if(currentId != -1){

            intent.putExtra("noteId", currentId)
            setResult(RESULT_OK, intent)
            finish()

        }

    }

    fun getAndSetData(){

        //get
        val currentTitle = intent.getStringExtra("currentTitle")
        val currentDescription = intent.getStringExtra("currentDescription")
        currentId = intent.getIntExtra("currentId", -1)

        editTextTitle.setText(currentTitle)
        editTextDescription.setText(currentDescription)

    }

}