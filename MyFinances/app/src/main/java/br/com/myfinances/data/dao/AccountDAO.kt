package br.com.myfinances.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import br.com.myfinances.data.entity.Account

@Dao
abstract class AccountDAO : BaseDAO<Account>(){

    @Query("SELECT * FROM Account WHERE categoryId = :category")
    abstract suspend fun findByCategory(category: Long): LiveData<List<Account>>
}