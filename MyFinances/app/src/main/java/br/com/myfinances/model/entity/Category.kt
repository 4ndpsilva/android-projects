package br.com.myfinances.model.entity

import androidx.room.Entity

@Entity(tableName = "TB_CATEGORY")
data class Category(var description: String) : BaseEntity()