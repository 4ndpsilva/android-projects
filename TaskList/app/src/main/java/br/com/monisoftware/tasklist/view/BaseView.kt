package br.com.monisoftware.tasklist.view

interface BaseView<T>{
    fun setPresenter(presenter: T)
}