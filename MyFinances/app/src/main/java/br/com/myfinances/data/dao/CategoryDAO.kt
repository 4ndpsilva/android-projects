package br.com.myfinances.data.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.myfinances.data.entity.Category

@Dao
abstract class CategoryDAO : GenericDAO<Category>("TB_CATEGORY"){
    @Query("SELECT * FROM TB_CATEGORY WHERE description LIKE :description")
    abstract suspend fun findByDescription(description: String): List<Category>
}