package br.com.mynotes.data.dao

import androidx.lifecycle.MutableLiveData
import br.com.mynotes.domain.Note

class FakeDAO {
    private val dataset: MutableLiveData<MutableList<Note>> = MutableLiveData()

    fun save(note: Note){
        var tmp = dataset.value

        if(tmp == null){
            tmp = mutableListOf()
            tmp.add(note)
        }
        else {
            tmp.add(note)
        }

        dataset.postValue(tmp)
    }

    fun list() = dataset
}