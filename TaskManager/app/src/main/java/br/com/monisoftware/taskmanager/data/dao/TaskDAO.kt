package br.com.monisoftware.taskmanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import br.com.monisoftware.taskmanager.data.entity.Task

@Dao
interface TaskDAO{
    @Insert(onConflict = REPLACE)
    fun save(user: Task): Long

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM TB_TASK ORDER BY done")
    fun findAll2(): List<Task>

    @Query("SELECT * FROM TB_TASK ORDER BY done")
    fun findAll(): LiveData<List<Task>>

    @Query("SELECT * FROM TB_TASK WHERE id = :id")
    fun findById(id: Long): Task

    @Query("SELECT COUNT(*) FROM TB_TASK WHERE done = :status")
    fun getRemainingTasks(status: Boolean): Int
}