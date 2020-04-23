package br.com.mynotes.ui.adapter

import br.com.mynotes.data.domain.Note

interface OnListItemListener {
    fun onItemClick(note: Note)
    fun onCheckedItems(vararg codes: Int)
}