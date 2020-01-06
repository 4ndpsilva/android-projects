package br.com.mynotes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.mynotes.R
import br.com.mynotes.data.dao.FakeDAO
import br.com.mynotes.domain.Note
import br.com.mynotes.viewmodel.NoteViewModel
import br.com.mynotes.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.form_note_activity.*
import java.util.*

class FormNoteActivity : AppCompatActivity(){
    private val factory = ViewModelFactory(FakeDAO())

    private val viewModel: NoteViewModel by lazy{
        ViewModelProviders.of(this, factory).get(NoteViewModel::class.java)
    }

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
        viewModel.save(note)
        finish()
    }
}