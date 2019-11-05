package br.com.myfinances.data.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date

class DateConverter {
    private val format = "dd/MM/yyyy"

    @TypeConverter
    fun fromString(date: String) = SimpleDateFormat(format).parse(date) ?: throw Exception()

    @TypeConverter
    fun fromDate(date: Date) = SimpleDateFormat(format).format(date) ?: throw Exception()
}