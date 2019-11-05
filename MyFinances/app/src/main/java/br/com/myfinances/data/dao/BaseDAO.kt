package br.com.myfinances.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDAO<T>{
    @Insert
    suspend fun save(entity: T)

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun delete(entity: T)
}