package br.com.monisoftware.tasklist

import android.app.Application
import br.com.monisoftware.tasklist.data.DatabaseApp

class App : Application(){
    companion object {
        lateinit var database: DatabaseApp
    }

    override fun onCreate() {
        super.onCreate()
        database = DatabaseApp(this)
    }
}