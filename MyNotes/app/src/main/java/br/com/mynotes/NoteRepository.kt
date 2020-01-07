package br.com.mynotes

import br.com.mynotes.data.dao.NoteDAO
import br.com.mynotes.domain.Note

class NoteRepository(private val dao: NoteDAO) {
    fun save(note: Note) = dao.save(note)

    fun findAll() = dao.findAll()
}