package br.com.mynotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.mynotes.NoteRepository

class ViewModelFactory(private val repo: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(clazz: Class<T>): T {
        return clazz.getConstructor(NoteRepository::class.java).newInstance(repo)
    }
}