package br.com.myfinances.model.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.myfinances.model.entity.Category

@Dao
interface CategoryDAO : BaseDAO<Category>{
    @Query("SELECT * FROM TB_CATEGORY WHERE id = :id")
    fun findById(id: Long): Category

    @Query("SELECT * FROM TB_CATEGORY")
    fun findAll(): List<Category>
}