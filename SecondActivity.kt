import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var textViewCounter: TextView
    private lateinit var counterReceiver: CounterBroadcastReceiver
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textViewCounter = findViewById(R.id.textViewCounter)
        counterReceiver = CounterBroadcastReceiver()

        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }

        registerReceiver(counterReceiver, IntentFilter(CounterService.ACTION_COUNTER_UPDATED))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(counterReceiver)
    }

    private fun updateCounter() {
        textViewCounter.text = "Counter: $counter"
    }

    inner class CounterBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == CounterService.ACTION_COUNTER_UPDATED) {
                counter = intent.getIntExtra(CounterService.EXTRA_COUNTER, 0)
                updateCounter()
            }
        }
    }
}
