package br.com.mynotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.mynotes.R
import br.com.mynotes.domain.Note

class NoteListAdapter : ListAdapter<Note, RecyclerView.ViewHolder>(NoteDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        val note = getItem(position)

        holder.bindView(dataset[position])
    }

    override fun getItemCount() = dataset.size

    /*
    fun setValues(values: List<Note>){
        dataset.clear()
        dataset.addAll(values)
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
*/

    private class NoteDiffCallback : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}