package br.com.mynotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mynotes.R
import br.com.mynotes.domain.Note

class NoteListAdapter : RecyclerView.Adapter<NoteViewHolder>(){
    private var dataset: MutableList<Note> = mutableListOf()
    private var indexItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) = holder.bindView(dataset[position])

    override fun getItemCount() = dataset.size

    fun setValues(values: List<Note>){
        dataset = values.toMutableList()
        notifyDataSetChanged()
    }

    fun add(item: Note){
        indexItem = dataset.indexOf(item)
        dataset.add(item)
        notifyItemInserted(indexItem)
    }

    fun remove(item: Note){
        indexItem = dataset.indexOf(item)
        dataset.remove(item)
        notifyItemRemoved(indexItem)
    }
}