package br.com.myfinances.model

import android.icu.util.ULocale
import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.myfinances.model.dao.AccountDAO
import br.com.myfinances.model.dao.CategoryDAO
import br.com.myfinances.model.dao.EntryDAO
import br.com.myfinances.model.entity.Account
import br.com.myfinances.model.entity.Category
import br.com.myfinances.model.entity.Entry

@Database(entities = [Category::class, Account::class, Entry::class], exportSchema = false)
abstract class DatabaseApp : RoomDatabase{
    abstract fun categoryDAO: CategoryDAO
    abstract fun accountDAO: AccountDAO
    abstract fun entryDAO: EntryDAO


}