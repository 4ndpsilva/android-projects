package br.com.myfinances.data.converter

import androidx.room.TypeConverter
import java.util.Calendar

class DateConverter {
    @TypeConverter
    fun fromCalendar(time: Calendar) = time.timeInMillis

    @TypeConverter
    fun toCalendar(time: Long): Calendar{
        val cal = Calendar.getInstance()
        cal.timeInMillis = time
        return cal
    }
}