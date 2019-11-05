package br.com.myfinances.data.dao

import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery

interface RawQueryDAO<T> {

    @RawQuery
    suspend fun findById(query : SupportSQLiteQuery): T

    @RawQuery
    suspend fun findAll(query : SupportSQLiteQuery): List<T>
}