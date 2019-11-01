package br.com.monisoftware.taskmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.monisoftware.taskmanager.data.dao.TaskDAO
import br.com.monisoftware.taskmanager.data.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class DatabaseApp : RoomDatabase(){
    abstract fun getDAO(): TaskDAO

    companion object{
        private const val DATABASE_NAME = "base"

        fun getInstance(cxt: Context): DatabaseApp {
            return synchronized(DatabaseApp::class){
                 Room.databaseBuilder(cxt, DatabaseApp::class.java, DATABASE_NAME)
                     .allowMainThreadQueries()
                     .build()
            }
        }

        fun inMemoryDatabase(cxt: Context): DatabaseApp{
            return Room.inMemoryDatabaseBuilder(cxt, DatabaseApp::class.java)
                .allowMainThreadQueries()
                .build()
        }
    }
}