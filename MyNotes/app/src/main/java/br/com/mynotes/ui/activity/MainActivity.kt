package br.com.mynotes.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mynotes.R
import br.com.mynotes.domain.Note
import br.com.mynotes.ui.adapter.NoteListAdapter
import br.com.mynotes.viewmodel.NoteViewModel
import br.com.mynotes.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.empty_list.*
import kotlinx.android.synthetic.main.form_note_activity.view.*
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class MainActivity : AppCompatActivity(){
    private lateinit var factory: ViewModelFactory

    private val viewModel: NoteViewModel by lazy{
        ViewModelProviders.of(this, factory).get(NoteViewModel::class.java)
    }

    private val listAdapter: NoteListAdapter by lazy{ NoteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(main_toolbar)

        initViewModel()
        initList()

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
        factory = ViewModelFactory(application)

        viewModel.dataset.observe(this) { listAdapter.s }
    }
    
    private fun createFormDialog(){
        val layout = LayoutInflater.from(this).inflate(R.layout.form_note_activity, null, false)

        val dialog = AlertDialog.Builder(this)
        dialog.setView(layout)
        dialog.setTitle(R.string.description)
        dialog.setNegativeButton(R.string.cancel, null)
        dialog.setPositiveButton(R.string.save){_, _ ->
            val note = Note()
            note.id = 5
            note.description = layout.ed_note.text.toString()
            note.date = Calendar.getInstance()
            viewModel.save(note)
        }
        dialog.create().show()
    }

    private fun initList(){
        rv_notes.adapter = listAdapter
        rv_notes.layoutManager = LinearLayoutManager(this)
    }
}