package br.com.tasklist.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.tasklist.R
import br.com.tasklist.repository.data.entity.Task
import br.com.tasklist.databinding.ActivityListBinding
import br.com.tasklist.ui.adapter.TaskListAdapter
import br.com.tasklist.ui.fragment.DeleteConfirmFragment
import br.com.tasklist.ui.listener.OnListItemListener
import br.com.tasklist.util.Constants
import br.com.tasklist.util.toast
import br.com.tasklist.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.empty_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity(), OnListItemListener<Task> {
    private val viewModel by viewModel<TaskViewModel>()
    private lateinit var binding: ActivityListBinding
    private val adapter: TaskListAdapter by lazy { TaskListAdapter(this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.lifecycleOwner = this
        setSupportActionBar(mainToolbar)

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.setHasFixedSize(true)
        rvTasks.adapter = adapter

        viewModel.tasks.observe(this, Observer{
            tvNoTasks.visibility = if(it.isNullOrEmpty()) View.VISIBLE else View.GONE
            adapter.submitList(it)
        })

        fbAdd.setOnClickListener{ showForm() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mn_done_all -> viewModel.update()
            R.id.mn_delete_all -> viewModel.delete()
            else -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(obj: Task) {
        showForm(obj)
    }

    override fun onItemLongClick(obj: Task) {
        showDeleteConfirmDialog(obj)
    }

    override fun onUpdateItem(obj: Task) {
        viewModel.update(obj)
        viewModel.remaining.observe(this, Observer{
            var msg = getString(R.string.all_done)

            if(it == 1) {
                msg = String.format(getString(R.string.remaining_task), it)
            }
            else if(it > 1) {
                msg = String.format(getString(R.string.remaining_tasks), it)
            }

            toast(this, msg)
        })
    }

    private fun showForm(task: Task? = null) {
        val intent = Intent(this, FormActivity::class.java)
        if(task != null) {
            intent.putExtra(Constants.TASK, task)
        }

        startActivity(intent)
    }

    private fun showDeleteConfirmDialog(task: Task) {
        val frag = DeleteConfirmFragment(this)
        val bundle = Bundle()
        bundle.putParcelable(Constants.TASK, task)
        frag.arguments = bundle
        frag.show(supportFragmentManager, "confirmDialog")
    }
}