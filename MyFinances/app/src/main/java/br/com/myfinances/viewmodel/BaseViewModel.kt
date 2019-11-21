package br.com.myfinances.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myfinances.data.repository.BaseRepository
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(private var repository: BaseRepository<T>) : ViewModel(){
    var list = MutableLiveData<List<T>>()
    var entity = MutableLiveData<T>()

    init{
        loadList()
    }

    private fun loadList(){
        val entities = repository.findAll()
        list.postValue(entities)
    }

    fun save(entity: T){
        viewModelScope.launch { repository.save(entity) }
    }

    fun update(entity: T){
        viewModelScope.launch { repository.update(entity) }
    }

    fun delete(entity: T){
        viewModelScope.launch { repository.delete(entity) }
    }

    fun findById(id: Long){
        viewModelScope.launch {
            val data = repository.findById(id)
            entity.postValue(data)
        }
    }
}