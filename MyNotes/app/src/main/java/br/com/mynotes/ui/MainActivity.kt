package br.com.mynotes.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mynotes.R
import br.com.mynotes.data.domain.Note
import br.com.mynotes.ui.adapter.NoteListAdapter
import br.com.mynotes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.form_note_activity.view.*
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class MainActivity : AppCompatActivity(){
    private val adapter: NoteListAdapter by lazy { NoteListAdapter(this) }
    private val viewModel: NoteViewModel by lazy{
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = adapter

        viewModel.notes.observe(this, Observer { data ->
            incEmptyList.visibility = if(data.isNullOrEmpty()) View.VISIBLE else View.GONE
            adapter.submitList(data)
        })

        btnAdd.setOnClickListener { showFormDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAll -> confirmDialog(R.string.confirm_delete_all){ viewModel.deleteAll() }
            R.id.exit -> confirmDialog(R.string.confirm_exit){ finish() }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun confirmDialog(msg: Int, func: () -> Unit){
        val confirmDialog = AlertDialog.Builder(this)
        confirmDialog.setTitle(R.string.warning)
        confirmDialog.setMessage(msg)
        confirmDialog.setNegativeButton(R.string.no, null)
        confirmDialog.setPositiveButton(R.string.yes){_, _ -> func.invoke() }
        confirmDialog.create().show()
    }

    private fun showFormDialog(){
        val layout = LayoutInflater.from(this).inflate(R.layout.form_note_activity, null, false)

        val dialog = AlertDialog.Builder(this)
        dialog.setView(layout)
        dialog.setTitle(R.string.new_note)
        dialog.setNegativeButton(R.string.cancel, null)
        dialog.setPositiveButton(R.string.save){_, _ ->
            val note = Note()
            note.description = layout.edNote.text.toString()
            note.date = Calendar.getInstance()
            viewModel.save(note)
        }
        dialog.create().show()
    }
}