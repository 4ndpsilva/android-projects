package br.com.mynotes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.mynotes.domain.Note

@Dao
interface NoteDAO {
    @Query("SELECT * FROM Note")
    fun findAll(): LiveData<List<Note>>

    @Delete
    suspend fun delete(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(note: Note): Long

    @Insert
    fun saveAll(vararg notes: List<Note>): List<Int>
}