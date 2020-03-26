package br.com.myfinances.data.repository

import br.com.myfinances.data.dao.BaseDAO

abstract class BaseRepository<T>(private val dao: BaseDAO<T>){
     fun save(entity: T) = dao.save(entity)

    fun update(entity: T) = dao.update(entity)

    fun delete(entity: T) = dao.delete(entity)

    fun findById(id: Long) = dao.findById(id)

    fun findAll() = dao.findAll()
}