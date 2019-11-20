package br.com.myfinances.data.repository

import br.com.myfinances.data.dao.GenericDAO

abstract class BaseRepository<T>(private val dao: GenericDAO<T>){
    suspend fun save(entity: T) = dao.save(entity)

    suspend fun update(entity: T) = dao.update(entity)

    suspend fun delete(entity: T) = dao.delete(entity)

    suspend fun findById(id: Long) = dao.findById(id)

    suspend fun findAll() = dao.findAll()
}