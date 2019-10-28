package br.com.monisoftware.taskmanager.presenter

import br.com.monisoftware.taskmanager.data.dao.TaskDAO
import br.com.monisoftware.taskmanager.data.entity.Task
import br.com.monisoftware.taskmanager.presenter.contract.ListContract

class ListPresenter : ListContract.Presenter<Task>{
    private val view: ListContract.View<Task>
    private val dao: TaskDAO

    constructor(view: ListContract.View<Task>, dao: TaskDAO){
        this.view = view
        this.dao = dao
        view.setPresenter(this)
    }

    override fun add() {
        view.showForm(0)
    }

    override fun populate() {
        dao.findAll().observeForever{tasks ->
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
        val task = dao.findById(id)
        dao.delete(task)
    }

    override fun update(task: Task): Int{
        dao.update(task)
        return dao.getRemainingTasks(false)
    }
}