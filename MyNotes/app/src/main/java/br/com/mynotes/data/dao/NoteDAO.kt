package br.com.mynotes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.mynotes.domain.Note

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(note: Note): Long

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM Note ORDER BY date")
    fun findAll(): LiveData<List<Note>>
}