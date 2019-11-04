package br.com.myfinances.model.entity

import androidx.room.Entity
import java.util.*

@Entity(tableName = "TB_ENTRY")
data class Entry(
    var date: Date,
    var account: Account,
    var category: Category,
    var operation: String = "",
    var value: Double = 0.0,
    var description: String = "") : BaseEntity()