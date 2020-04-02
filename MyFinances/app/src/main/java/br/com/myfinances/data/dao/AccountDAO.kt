package br.com.myfinances.data.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.myfinances.data.entity.Account

@Dao
abstract class AccountDAO : GenericDAO<Account>(){

    @Query("SELECT * FROM Account WHERE categoryId = :category")
    abstract fun findByCategory(category: Long): List<Account>
}