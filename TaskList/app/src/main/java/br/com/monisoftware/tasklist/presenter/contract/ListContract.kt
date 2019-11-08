package br.com.monisoftware.tasklist.presenter.contract

import br.com.monisoftware.tasklist.view.BaseView


interface ListContract{
    interface Presenter<T> {
        fun add()
        fun update(entity: T): Int
        fun delete(id: Long)
        fun populate()
        fun openForm(entity: T)
        fun openConfirmDeleteDialog(entity: T)
    }

    interface View<T> : BaseView<Presenter<T>> {
        fun setItems(items: List<T>)
        fun showForm(id: Long)
        fun showDeleteConfirmDialog(entity: T)
        fun showEmptyMessage()
    }

    interface OnItemClickListener<T>{
        fun onClickItem(entity: T)
        fun onClickLongItem(entity: T)
        fun onUpdateItem(entity: T)
    }

    interface DeleteListener{
        fun setConfirm(confirm: Boolean, id: Long)
    }
}