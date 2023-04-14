package com.example.roomdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdt: EditText
    lateinit var noteEdt: EditText
    lateinit var saveBtn: Button


    lateinit var viewModal: NoteViewModel
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)


        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)


        noteTitleEdt = findViewById(R.id.idEdtNoteName)
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        saveBtn = findViewById(R.id.idBtn)

        val notetype = intent.getStringExtra("NoteType")

        if(notetype.equals("Edit")){

            val noteTitle = intent.getStringExtra("noteTitle")
            val notedescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId",-1)

            saveBtn.text = "Update Note"
            noteTitleEdt.setText(noteTitle)
            noteEdt.setText(notedescription)
        }

        else{
            saveBtn.text = "Save Note"
        }


        saveBtn.setOnClickListener {
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteEdt.text.toString()

            if (notetype.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = noteID
                    viewModal.updateNode(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    // if the string is not empty we are calling a
                    // add note method to add data to our room database.
                    viewModal.AddNode(Note(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}