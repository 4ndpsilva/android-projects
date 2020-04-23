package br.com.tasklist.di

import br.com.tasklist.repository.TaskRepository
import br.com.tasklist.repository.data.DatabaseApp
import br.com.tasklist.viewmodel.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {
    val appModule = module{
        single { DatabaseApp(get()).getDAO() }

        single { TaskRepository(get()) }

        viewModel { TaskViewModel(get()) }
    }
}