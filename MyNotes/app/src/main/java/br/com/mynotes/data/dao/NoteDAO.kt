package br.com.mynotes.data.dao

import androidx.room.*
import br.com.mynotes.domain.Note

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(note: Note): Long

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM Note WHERE id = :id")
    fun findById(id: Long): Note

    @Query("SELECT * FROM Note ORDER BY date")
    fun findAll(): List<Note>
}