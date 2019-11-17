package br.com.monisoftware.tasklist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.monisoftware.tasklist.data.entity.Task

@Dao
interface TaskDAO{
    @Query("SELECT COUNT(*) FROM Task WHERE done = :status")
    fun getRemainingTasks(status: Boolean): Int

    @Query("SELECT * FROM Task WHERE id = :id")
    fun findById(id: Long): Task

    @Query("SELECT * FROM Task ORDER BY done")
    fun findAll(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: Task): Long

    @Update
    fun update(entity: Task)

    @Delete
    fun delete(entity: Task)

    @Query("DELETE FROM Task WHERE done = 1")
    fun delete()

    @Query("UPDATE Task SET done = 1")
    fun updateAllTasks()
}