package br.com.tasklist.repository.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.tasklist.repository.data.entity.Task

@Dao
interface TaskDAO{
    @Query("SELECT COUNT(*) FROM Task WHERE done = :status")
    fun getRemainingTasks(status: Boolean): LiveData<Int>

    @Query("SELECT * FROM Task WHERE id = :id")
    fun findById(id: Long): Task

    @Query("SELECT * FROM Task ORDER BY done")
    fun findAll(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: Task): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entities: List<Task>)

    @Update
    suspend fun update(entity: Task)

    @Delete
    suspend fun delete(entity: Task)

    @Query("DELETE FROM Task WHERE done = 1")
    suspend fun delete()

    @Query("UPDATE Task SET done = 1")
    suspend fun updateAllTasks()
}