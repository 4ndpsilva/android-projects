package br.com.mynotes.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var description: String = "",
    var date: Calendar = Calendar.getInstance())