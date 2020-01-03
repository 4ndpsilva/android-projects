package br.com.mynotes.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mynotes.domain.Note

class NoteAdapter(var dataset: MutableList<Note> = mutableListOf(), val cxt: Context) : RecyclerView.Adapter<NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

    }

    override fun getItemCount() = dataset.size

    fun setValues(values: MutableList<Note>){
        dataset = values
    }
}