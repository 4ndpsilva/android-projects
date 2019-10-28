package br.com.monisoftware.taskmanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_TASK")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var description: String = "",
    var done: Boolean = false)