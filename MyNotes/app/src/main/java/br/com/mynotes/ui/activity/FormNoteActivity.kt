package br.com.mynotes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mynotes.R
import br.com.mynotes.domain.Note
import kotlinx.android.synthetic.main.form_note_activity.*
import java.util.*

class FormNoteActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_note_activity)

        ed_note.requestFocus()

        val note = intent.getParcelableExtra<Note>("note")
        bt_save.setOnClickListener { onSave(note) }
    }

    private fun onSave(note: Note){
        note.description = ed_note.text.toString()
        note.date = Calendar.getInstance()
        finish()
    }
}