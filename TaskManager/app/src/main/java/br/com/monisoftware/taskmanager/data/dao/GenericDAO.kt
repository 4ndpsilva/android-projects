package br.com.monisoftware.taskmanager.data.dao

import androidx.lifecycle.LiveData
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import br.com.monisoftware.taskmanager.data.entity.Task
import java.lang.reflect.ParameterizedType

abstract class GenericDAO<T> : BaseDAO<T>{

    fun findById(id: Long): T{
        val query = SimpleSQLiteQuery("SELECT * FROM ${getTableName()} WHERE id = $id")
        return doFindById(query)
    }

    fun findAll(): LiveData<List<T>> {
        val query = SimpleSQLiteQuery("SELECT * FROM ${getTableName()}")
        return doFindAll(query)
    }

    private fun getTableName(): String{
        val paramType = javaClass.superclass?.genericSuperclass as ParameterizedType
        val clazz = paramType.actualTypeArguments[0] as Class<*>
        return clazz.simpleName
    }

    @RawQuery
    abstract fun doFindById(query: SupportSQLiteQuery): T

    @RawQuery(observedEntities = [Task::class])
    abstract fun doFindAll(query: SupportSQLiteQuery): LiveData<List<T>>
}