package br.com.mynotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.mynotes.data.dao.FakeDAO
import br.com.mynotes.domain.Note

class NoteViewModel(private val dao: FakeDAO) : ViewModel(){
    private var dataset: MutableLiveData<List<Note>>? = null
    private var data: MutableLiveData<Note>? = null

    fun getNotes(): LiveData<List<Note>> {
        if(dataset == null){
            dataset = MutableLiveData()
            load()
        }

        return dataset!!
    }

    fun getNote(): LiveData<Note> = data!!

    fun load(){
        val list = dao.list()
        dataset!!.postValue(list)
    }

    fun save(note: Note){
        dao.save(note)
        load()
    }
}