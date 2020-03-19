package br.com.mynotes.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.com.mynotes.data.DatabaseApp
import br.com.mynotes.repository.NoteRepository
import br.com.mynotes.domain.Note
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app){
    private val repository: NoteRepository = NoteRepository(DatabaseApp.getInstance(app).noteDAO())

    val dataset: LiveData<List<Note>> = repository.notes

    fun save(note: Note) {
        viewModelScope.launch{
            repository.save(note)
        }
    }
}