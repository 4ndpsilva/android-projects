package br.com.mynotes.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.mynotes.domain.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bindView(note: Note){
        with(itemView){
            tv_note.text = note.description
        }
    }
}