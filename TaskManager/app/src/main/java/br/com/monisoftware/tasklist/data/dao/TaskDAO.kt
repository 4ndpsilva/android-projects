package br.com.monisoftware.taskmanager.data.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.monisoftware.taskmanager.data.entity.Task

@Dao
abstract class TaskDAO : GenericDAO<Task>(){
    @Query("SELECT COUNT(*) FROM Task WHERE done = :status")
    abstract fun getRemainingTasks(status: Boolean): Int
}