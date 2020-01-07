package br.com.mynotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.mynotes.NoteRepository
import br.com.mynotes.domain.Note

class NoteViewModel(private val repo: NoteRepository) : ViewModel(){
    private var dataset: MutableLiveData<List<Note>>? = null

    fun getNotes(): LiveData<List<Note>>{
        if(dataset == null) {
            dataset = MutableLiveData()
        }

        dataset?.postValue(repo.findAll())

        return dataset!!
    }

    fun save(note: Note){
        repo.save(note)
    }
}