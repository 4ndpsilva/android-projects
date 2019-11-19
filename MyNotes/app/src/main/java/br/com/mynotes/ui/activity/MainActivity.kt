package br.com.mynotes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mynotes.R

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}