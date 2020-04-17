package br.com.mynotes.repository

import android.app.Application
import br.com.mynotes.data.DatabaseApp
import br.com.mynotes.data.domain.Note

class NoteRepository(app: Application){
    val notes = mutableListOf<Note>(
        Note("nota 1"),
        Note("nota 2"),
        Note("nota 3"),
        Note("nota 4"),
        Note("nota 5"),
        Note("nota 6")
    )

    private val dao = DatabaseApp.getInstance(app).noteDAO()

    fun getAll() = dao.getAll()

    suspend fun save(note: Note) = dao.save(note)

    suspend fun delete(note: Note) = dao.delete(note)

    suspend fun deleteAll() = dao.deleteAll()
}