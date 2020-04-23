package br.com.mynotes.repository

import br.com.mynotes.data.dao.NoteDAO
import br.com.mynotes.data.domain.Note

class NoteRepository(private val dao: NoteDAO){
    fun getAll() = dao.getAll()

    suspend fun save(note: Note) = dao.save(note)

    suspend fun delete(note: Note) = dao.delete(note)

    suspend fun deleteAll() = dao.deleteAll()
}