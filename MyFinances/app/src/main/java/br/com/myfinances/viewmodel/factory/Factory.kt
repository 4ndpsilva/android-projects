package br.com.myfinances.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.myfinances.repository.CategoryRepository
import br.com.myfinances.viewmodel.CategoryViewModel

class Factory(private var repository: CategoryRepository) : ViewModelProvider.Factory{
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        return CategoryViewModel(repository) as VM
    }
}