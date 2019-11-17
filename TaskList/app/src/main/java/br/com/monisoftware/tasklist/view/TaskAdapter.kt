package br.com.monisoftware.tasklist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.monisoftware.tasklist.R
import br.com.monisoftware.tasklist.data.entity.Task
import br.com.monisoftware.tasklist.presenter.contract.ListContract
import kotlinx.android.synthetic.main.list_item.view.*

class TaskAdapter(private val listener: ListContract.OnItemClickListener<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    private var items: List<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = items[position]
        holder.itemView.tvTask.text = task.description
        holder.itemView.swDone.isChecked = task.done
        holder.itemView.tvStatus.setText(if(task.done) R.string.done_task else R.string.pending_task)

        with(holder.itemView.context){
            val colorDoneTask = ContextCompat.getColor(this, R.color.colorDoneTask)
            val colorPendingTask = ContextCompat.getColor(this, R.color.colorPendingTask)
            holder.itemView.tvStatus.setTextColor(if(task.done) colorDoneTask else colorPendingTask)
        }

        holder.itemView.swDone.setOnClickListener {
            task.done = (it as Switch).isChecked
            listener.onUpdateItem(task)
            notifyItemChanged(position)
        }

        holder.itemView.setOnClickListener { listener.onClickItem(task) }
        holder.itemView.setOnLongClickListener { listener.onLongClickItem(task); true }
    }

    override fun getItemCount() = items.size

    fun setValues(values: List<Task>){
        items = values
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}