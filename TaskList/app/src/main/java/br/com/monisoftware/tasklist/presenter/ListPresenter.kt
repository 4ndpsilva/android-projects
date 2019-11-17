package br.com.monisoftware.tasklist.presenter

import br.com.monisoftware.tasklist.data.dao.TaskDAO
import br.com.monisoftware.tasklist.data.entity.Task
import br.com.monisoftware.tasklist.presenter.contract.ListContract
import java.lang.IllegalArgumentException

class ListPresenter : ListContract.Presenter<Task>{
    private val view: ListContract.View<Task>
    private val dao: TaskDAO?

    constructor(view: ListContract.View<Task>, dao: TaskDAO?){
        this.view = view
        this.dao = dao
        view.setPresenter(this)
    }

    override fun add() {
        view.showForm(0)
    }

    override fun populate() {
        dao?.findAll()?.observeForever{tasks ->
            view.setItems(tasks)
            if(tasks == null || tasks.isEmpty()){
                view.showEmptyMessage()
            }
        }
    }

    override fun openForm(task: Task) {
        view.showForm(task.id)
    }

    override fun openConfirmDeleteDialog(task: Task) {
        view.showDeleteConfirmDialog(task)
    }

    override fun delete(id: Long) {
        dao?.let {
            if(id > 0) {
                val task = it.findById(id)
                it.delete(task)
            }
            else{
                it.delete()
            }
        }
    }

    override fun update(task: Task?): Int{
        if(task != null){
            dao?.update(task)
        }
        else{
            dao?.updateAllTasks()
        }

        return dao?.getRemainingTasks(false) ?: 0
    }
}