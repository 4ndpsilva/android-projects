package br.com.myfinances.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.myfinances.data.converter.DateConverter
import br.com.myfinances.data.dao.AccountDAO
import br.com.myfinances.data.dao.CategoryDAO
import br.com.myfinances.data.dao.EntryDAO
import br.com.myfinances.data.entity.Account
import br.com.myfinances.data.entity.Category
import br.com.myfinances.data.entity.Entry

@Database(entities = [Category::class, Account::class, Entry::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DatabaseApp : RoomDatabase(){
    companion object{
        private const val DATABASE_NAME = "finances.db"

        @Volatile
        private var instance: DatabaseApp? = null

        operator fun invoke(context: Context) = instance ?: synchronized(this){
            instance ?: buildDatabase(context).also{ instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            DatabaseApp::class.java,
            DATABASE_NAME).build()
    }

    abstract fun categoryDAO(): CategoryDAO
    abstract fun accountDAO(): AccountDAO
    abstract fun entryDAO(): EntryDAO
}