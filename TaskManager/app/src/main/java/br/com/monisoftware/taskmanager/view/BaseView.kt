package br.com.monisoftware.taskmanager.view

interface BaseView<T>{
    fun setPresenter(presenter: T)
}