package br.com.myfinances.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "TB_ENTRY")
data class Entry(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var date: Date,

    @ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"])
    var categoryId: Long,

    @ForeignKey(entity = Account::class, parentColumns = ["id"], childColumns = ["accountId"])
    var accountId: Long,

    var operation: String = "",
    var value: Double = 0.0,
    var description: String = "")