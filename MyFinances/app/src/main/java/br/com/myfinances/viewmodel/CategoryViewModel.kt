package br.com.myfinances.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.myfinances.repository.CategoryRepository
import br.com.myfinances.rest.CategoryResponse
import br.com.myfinances.rest.CoroutinesWorker
import kotlinx.coroutines.Job

class CategoryViewModel(var repository: CategoryRepository) : ViewModel(){
    private lateinit var job: Job

    private var _categories = MutableLiveData<List<CategoryResponse>>()
    val categories: LiveData<List<CategoryResponse>>
        get() = _categories

    fun getAll(){
        job = CoroutinesWorker.ioThenMain(
            { repository.getAll() },
            { _categories.value = it }
        )
    }

    override fun onCleared() {
        if(::job.isInitialized){
            job.cancel()
        }
    }
}