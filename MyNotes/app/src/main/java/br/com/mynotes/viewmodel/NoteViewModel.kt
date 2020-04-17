package br.com.mynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.mynotes.data.domain.Note
import br.com.mynotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = NoteRepository(app)
    val notes = repository.getAll()

    fun save(note: Note) {
        viewModelScope.launch{
           repository.save(note)
        }
    }

    fun deleteAll(){
        viewModelScope.launch{
            repository.deleteAll()
        }
    }
}