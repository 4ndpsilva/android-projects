package app.financeapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        executar()
    }

    fun executar() {
        btCumprimentar.setOnClickListener {
            val hour = 6
            var msg = if (hour >= 18) R.string.boaNoite else R.string.bomDia

            val message = this.resources.getString(msg)
            Toast.makeText(this, "Olá ${message}", Toast.LENGTH_SHORT).show()
        }
    }
}