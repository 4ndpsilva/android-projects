package br.com.mynotes.repository

import br.com.mynotes.data.dao.NoteDAO
import br.com.mynotes.domain.Note

class NoteRepository(private val dao: NoteDAO) {
    val notes = dao.findAll()

    suspend fun save(note: Note) = dao.save(note)
}