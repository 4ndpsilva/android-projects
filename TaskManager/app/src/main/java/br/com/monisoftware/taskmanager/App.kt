package br.com.monisoftware.taskmanager

import android.app.Application
import br.com.monisoftware.taskmanager.data.DatabaseApp

class App : Application(){
    companion object {
        lateinit var database: DatabaseApp
    }

    override fun onCreate() {
        super.onCreate()
        database = DatabaseApp.getInstance(this)
    }
}