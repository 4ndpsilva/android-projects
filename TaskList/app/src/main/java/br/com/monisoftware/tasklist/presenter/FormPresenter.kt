package br.com.monisoftware.tasklist.presenter

import br.com.monisoftware.tasklist.data.dao.TaskDAO
import br.com.monisoftware.tasklist.data.entity.Task
import br.com.monisoftware.tasklist.presenter.contract.FormContract
import br.com.monisoftware.tasklist.util.Constants
import br.com.monisoftware.tasklist.util.StringUtil

class FormPresenter : FormContract.Presenter<Task>{
    private val view: FormContract.View<Task>
    private val dao: TaskDAO?

    constructor(view: FormContract.View<Task>, dao: TaskDAO?){
        this.view = view
        this.dao = dao
        view.setPresenter(this)
    }

    override fun save(task: Task) {
        if(task.id > 0){
            dao?.update(task)
            view.showSuccessMessage()
            view.close()
        }
        else {
            dao?.save(task)
            view.onNext()
            view.showSuccessMessage()
        }
    }

    override fun getEntityAndPopulate(id: Long) {
        val task = dao?.findById(id)
        if(task != null){
            view.populate(task)
        }
    }

    override fun validate(task: Task): Boolean {
        view.clearPreErrors()

        if(task.description.isEmpty()){
            view.showErrorMessage(Constants.FIELD_DESCRIPTION)
            return false
        }
        else if(!StringUtil.isLetterOrDigitOnly(task.description)){
            view.showErrorMessage(Constants.FIELD_LETTER_DIGIT_ONLY)
            return false
        }

        return true
    }
}