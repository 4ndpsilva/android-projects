package br.com.myfinances.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.myfinances.repository.BaseRepository

class ViewModelFactory<T : BaseRepository<T>>(var repo: T, var clazz: Class<T>? = null) : ViewModelProvider.Factory {
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        return modelClass.getConstructor(clazz).newInstance(repo)
    }
}