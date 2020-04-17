package br.com.mynotes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.mynotes.R
import br.com.mynotes.databinding.NoteItemBinding
import br.com.mynotes.data.domain.Note

class NoteListAdapter(var cxt: Context) : ListAdapter<Note, NoteViewHolder>(GenericDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<NoteItemBinding>(view, R.layout.note_item, parent, false)
        return NoteViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.animation = AnimationUtils.loadAnimation(cxt, R.anim.item_animation)
        holder.bindView(getItem(position))
    }
}