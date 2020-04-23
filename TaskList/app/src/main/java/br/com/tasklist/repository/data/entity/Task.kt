package br.com.tasklist.repository.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var description: String = "",
    var done: Boolean = false,
    var dateCreation: Calendar = Calendar.getInstance()
) : Parcelable {

    companion object {
        fun getList(): List<Task> {
            val tasks = listOf(
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                ),
                Task(
                    0,
                    "task",
                    false,
                    Calendar.getInstance()
                )
            )

            val newTaskList = mutableListOf<Task>()
            for((index, value) in tasks.withIndex()) {
                value.id = index + 1L
                newTaskList.add(value)
            }

            return newTaskList
        }
    }
}