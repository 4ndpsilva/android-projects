package br.com.myfinances.viewmodel

import android.content.Context
import br.com.myfinances.data.DatabaseApp
import br.com.myfinances.repository.AccountRepository
import br.com.myfinances.repository.EntryRepository

object Creator {
    /*
    fun vmFactoryCategory(cxt: Context) : ViewModelFactory<CategoryRepository>{
        val dao = DatabaseApp(cxt).categoryDAO()
        val repo = CategoryRepository(dao)
        return ViewModelFactory(repo)
    }

     */
    fun vmFactoryAccount(cxt: Context) : ViewModelFactory<AccountRepository>{
        val dao = DatabaseApp(cxt).accountDAO()
        val repo = AccountRepository(dao)
        return ViewModelFactory(repo)
    }

    fun vmFactoryEntry(cxt: Context) : ViewModelFactory<EntryRepository>{
        val dao = DatabaseApp(cxt).entryDAO()
        val repo = EntryRepository(dao)
        return ViewModelFactory(repo)
    }
}