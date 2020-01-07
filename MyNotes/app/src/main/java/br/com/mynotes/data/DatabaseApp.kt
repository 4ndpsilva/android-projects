package br.com.mynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.mynotes.data.converter.DateConverter
import br.com.mynotes.data.dao.NoteDAO
import br.com.mynotes.domain.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DatabaseApp : RoomDatabase(){
    companion object{
        private const val DATABASE_NAME = "notes.db"
        private var instance: DatabaseApp? = null

        fun getInstance(cxt: Context) = instance ?: synchronized(this){
            instance ?:  Room.databaseBuilder(cxt, DatabaseApp::class.java, DATABASE_NAME).build()
        }
    }

    abstract fun noteDAO(): NoteDAO
}