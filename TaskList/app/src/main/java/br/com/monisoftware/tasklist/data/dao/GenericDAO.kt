package br.com.monisoftware.tasklist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import br.com.monisoftware.tasklist.data.entity.Task
import java.lang.reflect.ParameterizedType

abstract class GenericDAO<T>{

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(entity: T): Long

    @Update
    abstract fun update(entity: T)

    @Delete
    abstract fun delete(entity: T)

    @RawQuery
    abstract fun doFindById(query: SupportSQLiteQuery): T

    @RawQuery(observedEntities = [Task::class])
    abstract fun doFindAll(query: SupportSQLiteQuery): LiveData<List<T>>
}