package br.com.monisoftware.taskmanager.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.monisoftware.taskmanager.App
import br.com.monisoftware.taskmanager.R
import br.com.monisoftware.taskmanager.data.entity.Task
import br.com.monisoftware.taskmanager.presenter.ListPresenter
import br.com.monisoftware.taskmanager.presenter.contract.ListContract
import br.com.monisoftware.taskmanager.util.Constants
import br.com.monisoftware.taskmanager.view.TaskAdapter
import br.com.monisoftware.taskmanager.view.fragment.DeleteConfirmFragment
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.empty_list.*
import kotlinx.android.synthetic.main.list_activity.*

class ListActivity : AppCompatActivity(), ListContract.View<Task>, ListContract.OnItemClickListener<Task>,
    ListContract.DeleteListener{

    private lateinit var presenter: ListContract.Presenter<Task>
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        val dao = App.database.getDAO()
        presenter = ListPresenter(this, dao)
        initView()
    }

    override fun onStart() {
        super.onStart()
        presenter.populate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.mn_exit){
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showForm(id: Long) {
        val intent = Intent(this, FormActivity::class.java)
        if(id > 0) {
            intent.putExtra(Constants.ID, id)
        }

        startActivity(intent)
    }

    override fun showDeleteConfirmDialog(task: Task) {
        val frag = DeleteConfirmFragment(this)
        val bundle = Bundle()
        bundle.putLong(Constants.ID, task.id)
        frag.arguments = bundle
        frag.show(supportFragmentManager, "confirmDialog")
    }

    override fun showEmptyMessage() {
        tvNoTasks.visibility = View.VISIBLE
    }

    override fun setItems(tasks: List<Task>) {
        tvNoTasks.visibility = View.GONE
        taskAdapter.setValues(tasks)
    }

    override fun setPresenter(presenter: ListContract.Presenter<Task>) {
        this.presenter = presenter
    }

    override fun onClickItem(task: Task) {
        presenter.openForm(task)
    }

    override fun onClickLongItem(task: Task) {
        presenter.openConfirmDeleteDialog(task)
    }

    override fun setConfirm(confirm: Boolean, id: Long) {
        if(confirm){
            presenter.delete(id)
        }
    }

    override fun onUpdateItem(task: Task) {
        val remainingTasks = presenter.update(task)
        lateinit var msg: String

        if(remainingTasks == 1) {
            msg = String.format(getString(R.string.remaining_task), remainingTasks)
        }
        else if(remainingTasks > 1) {
            msg = String.format(getString(R.string.remaining_tasks), remainingTasks)
        }
        else {
            msg = getString(R.string.all_done_tasks)
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initView(){
        setSupportActionBar(toolbarMain)

        fbAdd.setOnClickListener{ presenter.add() }
        initList()
    }

    private fun initList(){
        taskAdapter = TaskAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        rvTasks.setHasFixedSize(true)
        rvTasks.apply {
            setLayoutManager(layoutManager)
            adapter = taskAdapter
        }
    }
}