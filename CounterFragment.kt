import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment : Fragment() {

    private lateinit var textViewCounter: TextView
    private lateinit var buttonIncrement: Button
    private var counter = 0
    private lateinit var counterReceiver: CounterBroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_counter, container, false)

        textViewCounter = view.findViewById(R.id.textViewCounter)
        buttonIncrement = view.findViewById(R.id.buttonIncrement)

        counterReceiver = CounterBroadcastReceiver()

        buttonIncrement.setOnClickListener {
            counter++
            updateCounter()
            sendCounterToService()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        requireContext().registerReceiver(counterReceiver, IntentFilter(CounterService.ACTION_COUNTER_UPDATED))
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(counterReceiver)
    }

    private fun updateCounter() {
        textViewCounter.text = "Counter: $counter"
    }

    private fun sendCounterToService() {
        val intent = Intent(requireContext(), CounterService::class.java)
        intent.putExtra(CounterService.EXTRA_COUNTER, counter)
        requireContext().startService(intent)
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
