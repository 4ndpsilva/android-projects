package br.com.myfinances.data.repository

import br.com.myfinances.data.dao.GenericDAO

class RepositoryFactory<D : GenericDAO<D>>(dao: D) {
    companion object {
        fun <R : BaseRepository<*>> getInstance(clazz: Class<R>): R {
            return when(clazz.name){
                CategoryRepository::class.java.name -> clazz.getConstructor(R::class.java).newInstance(dao)
            }
        }
    }
}