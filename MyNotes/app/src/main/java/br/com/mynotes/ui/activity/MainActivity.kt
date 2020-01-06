package br.com.mynotes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mynotes.R
import br.com.mynotes.data.dao.FakeDAO
import br.com.mynotes.domain.Note
import br.com.mynotes.ui.adapter.NoteListAdapter
import br.com.mynotes.viewmodel.NoteViewModel
import br.com.mynotes.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.empty_list.*
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(){
    private val factory = ViewModelFactory(FakeDAO())

    private val viewModel: NoteViewModel by lazy{
        ViewModelProviders.of(this, factory).get(NoteViewModel::class.java)
    }

    private val listAdapter: NoteListAdapter by lazy{ NoteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(main_toolbar)

        rv_notes.adapter = listAdapter
        rv_notes.layoutManager = LinearLayoutManager(this)

        initViewModel()

        bt_add.setOnClickListener {
            val intent = Intent(this, FormNoteActivity::class.java)
            intent.putExtra("note", Note())
            startActivity(intent)
        }
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
        viewModel.getNotes().observeForever{ data ->
            data?.let {
                if(it.isEmpty()){
                    tv_no_notes.visibility = View.VISIBLE
                    rv_notes.visibility = View.GONE
                }
                else{
                    tv_no_notes.visibility = View.GONE
                    rv_notes.visibility = View.VISIBLE
                    listAdapter.setValues(it)
                }
            }
        }
    }
}