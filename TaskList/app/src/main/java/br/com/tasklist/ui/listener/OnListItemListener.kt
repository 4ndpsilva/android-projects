package br.com.tasklist.ui.listener

interface OnListItemListener<T>{
    fun onItemClick(obj: T)
    fun onItemLongClick(obj: T)
    fun onUpdateItem(obj: T)
}

