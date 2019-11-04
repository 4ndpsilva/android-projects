package br.com.myfinances.model.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import br.com.myfinances.model.entity.BaseEntity

interface BaseDAO<T : BaseEntity>{
    @Insert
    fun save(entity: T)

    @Update
    fun update(entity: T)

    @Delete
    fun delete(entity: T)
}