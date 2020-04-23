package br.com.tasklist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.tasklist.R
import br.com.tasklist.databinding.ListItemBinding
import br.com.tasklist.repository.data.entity.Task
import br.com.tasklist.ui.listener.OnListItemListener

class TaskListAdapter(private var cxt: Context,
                      private val listener: OnListItemListener<Task>
) : ListAdapter<Task, TaskViewHolder>(GenericDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<ListItemBinding>(view, R.layout.list_item, parent,false)

        return TaskViewHolder(dataBinding, cxt, listener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int){
        holder.itemView.animation = AnimationUtils.loadAnimation(cxt, R.anim.list_animation)
        holder.bindView(getItem(position))
    }
}