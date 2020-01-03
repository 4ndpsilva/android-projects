package br.com.myfinances.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myfinances.data.repository.BaseRepository
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(private var repository: BaseRepository<T>) : ViewModel(){
    private var list = MutableLiveData<List<T>>()
    private var entity = MutableLiveData<T>()

    fun loadList(): MutableLiveData<List<T>>{
        val entities = repository.findAll()
        list.postValue(entities)
        return list
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