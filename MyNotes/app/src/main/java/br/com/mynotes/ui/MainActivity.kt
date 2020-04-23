package br.com.mynotes.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mynotes.R
import br.com.mynotes.data.domain.Note
import br.com.mynotes.ui.adapter.NoteListAdapter
import br.com.mynotes.ui.adapter.OnListItemListener
import br.com.mynotes.ui.viewmodel.NoteViewModel
import br.com.mynotes.util.toast
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnListItemListener{
    private val viewModel by viewModel<NoteViewModel>()
    private val adapter: NoteListAdapter by lazy { NoteListAdapter(this, this) }

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

        viewModel.valid.observe(this, Observer{
            val msg = if(it) R.string.success_saved else R.string.required_field
            toast(msg)
        })

        btnAdd.setOnClickListener { showForm() }
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

    override fun onItemClick(note: Note) = showForm(note)

    override fun onCheckedItems(vararg codes: Int) {
        TODO("Not yet implemented")
    }

    private fun confirmDialog(msg: Int, func: () -> Unit){
        val confirmDialog = AlertDialog.Builder(this)
        confirmDialog.setTitle(R.string.warning)
        confirmDialog.setMessage(msg)
        confirmDialog.setNegativeButton(R.string.no, null)
        confirmDialog.setPositiveButton(R.string.yes){_, _ -> func.invoke() }
        confirmDialog.create().show()
    }

    private fun showForm(note: Note = Note()){
        val form = FormDialogFragment()
        form.arguments = Bundle().apply { putParcelable("NOTE", note) }
        form.setVM(viewModel)
        form.show(supportFragmentManager, "formDialog")
    }
}