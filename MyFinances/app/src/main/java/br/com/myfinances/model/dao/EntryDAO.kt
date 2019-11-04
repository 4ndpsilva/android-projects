package br.com.myfinances.model.dao

import androidx.room.Query
import br.com.myfinances.model.entity.Entry

interface EntryDAO : BaseDAO<Entry>{
    @Query("SELECT * FROM TB_ENTRY WHERE id = :id")
    fun findById(id: Long): Entry

    @Query("SELECT * FROM TB_ENTRY")
    fun findAll(): List<Entry>
}