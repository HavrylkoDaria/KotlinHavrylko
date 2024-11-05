import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var textViewCounter: TextView
    private lateinit var buttonIncrement: Button
    private var counter = 0
    private lateinit var counterReceiver: CounterBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCounter = findViewById(R.id.textViewCounter)
        buttonIncrement = findViewById(R.id.buttonIncrement)

        counterReceiver = CounterBroadcastReceiver()

        buttonIncrement.setOnClickListener {
            counter++
            updateCounter()
            sendCounterToService()
        }

        findViewById<Button>(R.id.buttonNavigateToFragment).setOnClickListener {
            val fragment = CounterFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        registerReceiver(counterReceiver, IntentFilter(CounterService.ACTION_COUNTER_UPDATED))
        startService(Intent(this, CounterService::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(counterReceiver)
    }

    private fun updateCounter() {
        textViewCounter.text = "Counter: $counter"
    }

    private fun sendCounterToService() {
        val intent = Intent(this, CounterService::class.java)
        intent.putExtra(CounterService.EXTRA_COUNTER, counter)
        startService(intent)
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

