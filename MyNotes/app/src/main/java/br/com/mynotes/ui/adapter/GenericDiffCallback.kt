package br.com.mynotes.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.mynotes.data.domain.BaseEntity

class GenericDiffCallback<T : BaseEntity> : DiffUtil.ItemCallback<T>() {
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}