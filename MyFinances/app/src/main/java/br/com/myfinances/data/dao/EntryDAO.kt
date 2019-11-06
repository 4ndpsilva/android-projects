package br.com.myfinances.data.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.myfinances.data.entity.Entry
import java.util.*

@Dao
abstract class EntryDAO : GenericDAO<Entry>(){

    @Query("SELECT * FROM Entry WHERE date BETWEEN :start AND :end")
    abstract suspend fun findByDate(start: Date, end: Date): List<Entry>
}