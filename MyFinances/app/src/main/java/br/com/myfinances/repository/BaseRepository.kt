package br.com.myfinances.repository

import br.com.myfinances.data.dao.BaseDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class BaseRepository<T>(val dao: BaseDAO<T>){
    open fun save(entity: T){
        GlobalScope.launch {
            dao.save(entity)
        }
    }

    open fun update(entity: T){

    }

    open fun delete(entity: T){

    }
}