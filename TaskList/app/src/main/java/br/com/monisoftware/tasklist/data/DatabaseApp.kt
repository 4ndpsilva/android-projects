package br.com.monisoftware.tasklist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.monisoftware.tasklist.data.dao.TaskDAO
import br.com.monisoftware.tasklist.data.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class DatabaseApp : RoomDatabase(){
    companion object{
        private const val DATABASE_NAME = "tasks.db"
        private var instance: DatabaseApp? = null

        operator fun invoke(cxt: Context) = instance ?: synchronized(this){
            instance ?: getInstance(cxt)
        }

        private fun getInstance(cxt: Context): DatabaseApp {
            return synchronized(DatabaseApp::class){
                 Room.databaseBuilder(cxt, DatabaseApp::class.java, DATABASE_NAME)
                     .allowMainThreadQueries()
                     .build()
            }
        }

        fun inMemoryDatabase(cxt: Context) = Room
            .inMemoryDatabaseBuilder(cxt, DatabaseApp::class.java)
            .build()
    }

    abstract fun getDAO(): TaskDAO
}