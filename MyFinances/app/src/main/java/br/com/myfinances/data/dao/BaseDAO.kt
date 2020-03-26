package br.com.myfinances.data.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import java.lang.reflect.ParameterizedType

abstract class BaseDAO<T>{

    fun findById(id: Long): T{
        val query = SimpleSQLiteQuery("SELECT * FROM ${getTableName()} WHERE id = $id")
        return doFindById(query)
    }

    fun findAll(): List<T>{
        val query = SimpleSQLiteQuery("SELECT * FROM ${getTableName()}")
        return doFindAll(query)
    }

    private fun getTableName(): String{
        val paramType = javaClass.superclass?.genericSuperclass as ParameterizedType
        val clazz = paramType.actualTypeArguments[0] as Class<T>
        return clazz.simpleName
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(entity: T)

    @Update
    abstract fun update(entity: T)

    @Delete
    abstract fun delete(entity: T)

    @RawQuery
    abstract fun doFindById(query : SupportSQLiteQuery): T

    @RawQuery
    abstract fun doFindAll(query : SupportSQLiteQuery): List<T>
}