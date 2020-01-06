package br.com.mynotes.data.dao

import br.com.mynotes.domain.Note
import java.util.*

class FakeDAO {
    private val dataset = mutableListOf(
        Note(1, "note 1", Calendar.getInstance()),
        Note(2, "note 2", Calendar.getInstance()),
        Note(3, "note 3", Calendar.getInstance()),
        Note(4, "note 4", Calendar.getInstance()),
        Note(5, "note 5", Calendar.getInstance()),
        Note(6, "note 6", Calendar.getInstance())
    )

    fun save(note: Note) = dataset.add(note)

    fun delete(note: Note) = dataset.remove(note)

    fun list() = dataset
}