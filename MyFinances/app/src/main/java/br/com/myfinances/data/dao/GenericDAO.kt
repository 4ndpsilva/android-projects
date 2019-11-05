package br.com.myfinances.data.dao

import androidx.sqlite.db.SimpleSQLiteQuery

abstract class GenericDAO<T>(var tableName: String = "") : BaseDAO<T>, RawQueryDAO<T>{
    private val rawDAO: RawQueryDAO<T> = this

    suspend fun findById(id: Long): T{
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE id = $id")
        return rawDAO.findById(query)
    }

    suspend fun findAll(): List<T>{
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName")
        return rawDAO.findAll(query)
    }
}