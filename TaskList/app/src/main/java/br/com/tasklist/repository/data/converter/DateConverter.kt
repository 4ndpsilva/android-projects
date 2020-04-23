package br.com.tasklist.repository.data.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toTimestamp(date: Calendar) = date.timeInMillis

    @TypeConverter
    fun fromTimestamp(value: Long): Calendar {
        val c = Calendar.getInstance()
        c.timeInMillis = value
        return c
    }
}