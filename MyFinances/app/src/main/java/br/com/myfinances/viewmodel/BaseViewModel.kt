package br.com.myfinances.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myfinances.repository.BaseRepository
import kotlinx.coroutines.launch

class BaseViewModel<T : BaseRepository<T>>(private var repository: T) : ViewModel() {
    private var list = MutableLiveData<List<T>>()
    private var entity = MutableLiveData<T>()

    suspend fun loadList(){
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