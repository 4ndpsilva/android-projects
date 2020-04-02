package br.com.myfinances.data.repository

import br.com.myfinances.data.dao.GenericDAO

class RepositoryFactory<T : GenericDAO<T>>(dao: T) {
    companion object {
        fun <R : BaseRepository<*>> getInstance(clazz: Class<*>): R? {
            return null
        }
    }
}