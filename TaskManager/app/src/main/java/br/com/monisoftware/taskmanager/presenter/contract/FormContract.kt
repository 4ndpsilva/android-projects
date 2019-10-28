package br.com.monisoftware.taskmanager.presenter.contract

import br.com.monisoftware.taskmanager.view.BaseView

interface FormContract {
    interface Presenter<T> {
        fun save(entity: T)
        fun getEntityAndPopulate(id: Long)
        fun validate(entity: T): Boolean
    }

    interface View<T> : BaseView<Presenter<T>> {
        fun populate(entity: T)
        fun showErrorMessage(field: Int)
        fun showSuccessMessage()
        fun clearPreErrors()
        fun close()
        fun onNext()
    }
}