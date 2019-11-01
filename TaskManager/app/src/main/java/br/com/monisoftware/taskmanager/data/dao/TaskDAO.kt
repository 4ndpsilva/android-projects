package br.com.monisoftware.taskmanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.monisoftware.taskmanager.data.entity.Task

@Dao
interface TaskDAO : BaseDAO<Task>{

    @Query("SELECT * FROM TB_TASK ORDER BY done")
    fun findAll(): LiveData<List<Task>>

    @Query("SELECT * FROM TB_TASK WHERE id = :id")
    fun findById(id: Long): Task

    @Query("SELECT COUNT(*) FROM TB_TASK WHERE done = :status")
    fun getRemainingTasks(status: Boolean): Int
}