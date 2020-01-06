package br.com.mynotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.mynotes.data.dao.FakeDAO

class ViewModelFactory(private val dao: FakeDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(clazz: Class<T>): T {
        return clazz.getConstructor(FakeDAO::class.java).newInstance(dao)
    }
}