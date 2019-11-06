package br.com.monisoftware.taskmanager.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: T): Long

    @Update
    fun update(entity: T)

    @Delete
    fun delete(entity: T)
}