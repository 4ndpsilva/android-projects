package br.com.myfinances.model.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.myfinances.model.entity.Account
import br.com.myfinances.model.entity.Category

@Dao
interface AccountDAO : BaseDAO<Account>{
    @Query("SELECT * FROM TB_ACCOUNT WHERE id = :id")
    fun findById(id: Long): Category

    @Query("SELECT * FROM TB_ACCOUNT")
    fun findAll(): List<Category>
}