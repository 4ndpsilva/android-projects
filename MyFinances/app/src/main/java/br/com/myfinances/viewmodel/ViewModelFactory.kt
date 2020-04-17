package br.com.myfinances.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.myfinances.repository.BaseRepository

class ViewModelFactory<T : BaseRepository<*>>(var repo: T) : ViewModelProvider.Factory {
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        return modelClass.getConstructor(repo.javaClass).newInstance(repo)
    }
}