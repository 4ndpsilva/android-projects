package br.com.tasklist.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.tasklist.repository.data.entity.Task

class GenericDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
}