package br.com.mynotes.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mynotes.R
import br.com.mynotes.domain.Note
import br.com.mynotes.ui.adapter.NoteListAdapter
import br.com.mynotes.viewmodel.NoteViewModel
import br.com.mynotes.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.form_note_activity.view.*
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class MainActivity : AppCompatActivity(){

    private val viewModel: NoteViewModel by lazy{
        ViewModelProviders.of(this, ViewModelFactory(application)).get(NoteViewModel::class.java)
    }

    private lateinit var listAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(main_toolbar)

        initList()
        initViewModel()

        bt_add.setOnClickListener { createFormDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.exit){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel(){
        viewModel.notes.observe(this, Observer { data ->
            listAdapter.setValues(data)
        })
    }
    
    private fun createFormDialog(){
        val layout = LayoutInflater
            .from(this)
            .inflate(R.layout.form_note_activity, null, false)

        val dialog = AlertDialog.Builder(this)
        dialog.setView(layout)
        dialog.setNegativeButton(R.string.cancel, null)
        dialog.setPositiveButton(R.string.save){_, _ ->
            val note = Note()
            note.id = 5
            note.description = layout.ed_note.text.toString()
            note.date = Calendar.getInstance()
            viewModel.save(note)
            listAdapter.add(note)
        }
        dialog.create().show()
    }

    private fun initList(){
        listAdapter = NoteListAdapter()
        rv_notes.layoutManager = LinearLayoutManager(this)
        rv_notes.setHasFixedSize(true)
        rv_notes.adapter = listAdapter
    }
}