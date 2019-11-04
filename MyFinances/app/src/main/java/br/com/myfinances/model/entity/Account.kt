package br.com.myfinances.model.entity

import androidx.room.Entity

@Entity(tableName = "TB_ACCOUNT")
data class Account(var description: String, var category: Category) : BaseEntity()