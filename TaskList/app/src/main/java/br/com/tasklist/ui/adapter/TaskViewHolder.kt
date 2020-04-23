package br.com.tasklist.ui.adapter

import android.content.Context
import android.widget.Switch
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.tasklist.R
import br.com.tasklist.databinding.ListItemBinding
import br.com.tasklist.repository.data.entity.Task
import br.com.tasklist.ui.listener.OnListItemListener
import java.text.SimpleDateFormat

class TaskViewHolder(private var dataBinding: ListItemBinding,
                     private var cxt: Context,
                     private val listener: OnListItemListener<Task>
) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bindView(task: Task){
        dataBinding.tvLabelDescription.text = task.description
        dataBinding.swDone.isChecked = task.done
        dataBinding.tvStatus.setText(if(task.done) R.string.done_task else R.string.pending_task)

        val colorDoneTask = ContextCompat.getColor(cxt, R.color.colorDoneTask)
        val colorPendingTask = ContextCompat.getColor(cxt, R.color.colorPendingTask)
        dataBinding.tvStatus.setTextColor(if(task.done) colorDoneTask else colorPendingTask)

        dataBinding.swDone.setOnClickListener {
            task.done = (it as Switch).isChecked
            listener.onUpdateItem(task)
        }

        dataBinding.root.setOnClickListener { listener.onItemClick(task) }
        dataBinding.root.setOnLongClickListener { listener.onItemLongClick(task); true }

        val label = cxt.getString(R.string.created_in)
        dataBinding.tvLabelDate.text = "$label ${SimpleDateFormat("dd/MM/yyyy").format(task.dateCreation.time)}"
    }
}