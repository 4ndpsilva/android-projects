package br.com.tasklist.repository

import br.com.tasklist.repository.data.dao.TaskDAO
import br.com.tasklist.repository.data.entity.Task
import java.util.*

class TaskRepository(private val dao: TaskDAO) {
    fun getRemainingTasks(status: Boolean) = dao.getRemainingTasks(status)

    fun findById(id: Long) = dao.findById(id)

    fun findAll() = dao.findAll()

    suspend fun save(task: Task): Long{
        task.dateCreation = Calendar.getInstance()
        return dao.save(task)
    }

    suspend fun update(entity: Task) = dao.update(entity)

    suspend fun delete(entity: Task) = dao.delete(entity)

    suspend fun delete() = dao.delete()

    suspend fun updateAllTasks() = dao.updateAllTasks()
}