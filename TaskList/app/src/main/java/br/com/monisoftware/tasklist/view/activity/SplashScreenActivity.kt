package br.com.monisoftware.tasklist.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.monisoftware.tasklist.R

class SplashScreenActivity : AppCompatActivity(){
    private lateinit var handler: Handler
    private val splashTime = 3000L

    private val running = Runnable{
        if(!isFinishing){
            startActivity(Intent(this@SplashScreenActivity, ListActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        handler = Handler()
        handler.postDelayed(running, splashTime)
    }

    override fun onDestroy() {
        handler.removeCallbacks(running)
        super.onDestroy()
    }
}