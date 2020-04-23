package br.com.mynotes.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.mynotes.data.domain.Note
import br.com.mynotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val repository: NoteRepository) : AndroidViewModel(app) {
    val notes = repository.getAll()
    private val _valid = MutableLiveData<Boolean>()
    val valid: LiveData<Boolean>
        get() = _valid

    fun save(note: Note) {
        viewModelScope.launch{
            _valid.postValue(false)
            if(note.description.isNotEmpty()){
                repository.save(note)
                _valid.postValue(true)
            }
        }
    }

    fun deleteAll(){
        viewModelScope.launch{
            repository.deleteAll()
        }
    }
}