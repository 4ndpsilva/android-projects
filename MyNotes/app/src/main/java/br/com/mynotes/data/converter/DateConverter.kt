package br.com.mynotes.data.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter{

    @TypeConverter
    fun toTimestamp(date: Calendar) = date.timeInMillis

    @TypeConverter
    fun fromTimestamp(time: Long): Calendar{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return calendar
    }
}