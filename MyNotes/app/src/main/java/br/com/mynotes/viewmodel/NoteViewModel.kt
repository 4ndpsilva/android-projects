package br.com.mynotes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.mynotes.data.DatabaseApp
import br.com.mynotes.domain.Note
import br.com.mynotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app){
    private val repository = NoteRepository(DatabaseApp.getInstance(app).noteDAO())

    val notes = repository.findAll()

    fun save(note: Note){
        viewModelScope.launch {
            repository.save(note)
            Log.i("TAG", notes.value?.size.toString())
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch { repository.delete(note) }
    }
}