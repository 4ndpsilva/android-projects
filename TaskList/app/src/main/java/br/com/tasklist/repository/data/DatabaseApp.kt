package br.com.tasklist.repository.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.tasklist.repository.data.converter.DateConverter
import br.com.tasklist.repository.data.dao.TaskDAO
import br.com.tasklist.repository.data.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DatabaseApp : RoomDatabase(){
    companion object{
        private const val DATABASE_NAME = "tasks.db"
        private var instance: DatabaseApp? = null

        operator fun invoke(cxt: Context) = instance
            ?: synchronized(this){
            instance
                ?: getInstance(
                    cxt
                )
        }

        private fun getInstance(cxt: Context): DatabaseApp {
            return synchronized(DatabaseApp::class){
                 Room.databaseBuilder(cxt, DatabaseApp::class.java,
                     DATABASE_NAME
                 )
                     .addCallback(object: RoomDatabase.Callback(){
                         override fun onCreate(db: SupportSQLiteDatabase) {
                             super.onCreate(db)
                             GlobalScope.launch(Dispatchers.IO){
                                 getInstance(
                                     cxt
                                 )
                                     .getDAO().save(Task.getList())
                             }
                         }
                     }).build()
            }
        }

        fun inMemoryDatabase(cxt: Context) = Room
            .inMemoryDatabaseBuilder(cxt, DatabaseApp::class.java)
            .build()
    }

    abstract fun getDAO(): TaskDAO
}