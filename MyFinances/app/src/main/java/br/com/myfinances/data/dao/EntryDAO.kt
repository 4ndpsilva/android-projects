package br.com.myfinances.data.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.myfinances.data.entity.Entry
import java.util.*

@Dao
abstract class EntryDAO : GenericDAO<Entry>("TB_ENTRY"){

    @Query("SELECT * FROM TB_ENTRY WHERE date BETWEEN :start AND :end")
    abstract suspend fun findByDate(start: Date, end: Date): List<Entry>
}