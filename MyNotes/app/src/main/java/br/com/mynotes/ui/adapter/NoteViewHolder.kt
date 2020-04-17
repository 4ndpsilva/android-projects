package br.com.mynotes.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.mynotes.databinding.NoteItemBinding
import br.com.mynotes.data.domain.Note

class NoteViewHolder(private val dataBinding: NoteItemBinding) : RecyclerView.ViewHolder(dataBinding.root){
    fun bindView(note: Note){
        dataBinding.note = note
    }
}