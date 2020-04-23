package br.com.mynotes.di

import br.com.mynotes.data.DatabaseApp
import br.com.mynotes.repository.NoteRepository
import br.com.mynotes.ui.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {
    val appModule = module{
        single{ DatabaseApp.getInstance(get()).noteDAO() }

        single { NoteRepository(get()) }

        viewModel { NoteViewModel(get(), get()) }
    }
}