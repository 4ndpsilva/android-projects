package br.com.mynotes

import android.app.Application
import br.com.mynotes.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(MainModule.appModule)
        }
    }
}