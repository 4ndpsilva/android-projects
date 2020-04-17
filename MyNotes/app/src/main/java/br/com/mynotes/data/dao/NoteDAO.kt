package br.com.mynotes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.mynotes.data.domain.Note

@Dao
interface NoteDAO {
    @Query("SELECT * FROM Note")
    fun getAll(): LiveData<List<Note>>

    @Delete
    suspend fun delete(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(note: Note): Long

    @Query("DELETE FROM Note")
    suspend fun deleteAll()
}