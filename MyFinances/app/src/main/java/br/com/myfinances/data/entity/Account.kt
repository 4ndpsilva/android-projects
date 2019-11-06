package br.com.myfinances.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var description: String,

    @ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"])
    var categoryId: Long)