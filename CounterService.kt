import android.app.Service
import android.content.Intent
import android.os.IBinder

class CounterService : Service() {

    private var counter = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        counter = intent?.getIntExtra(EXTRA_COUNTER, counter) ?: counter
        sendCounterUpdate()
        return START_NOT_STICKY
    }

    private fun sendCounterUpdate() {
        val intent = Intent(ACTION_COUNTER_UPDATED)
        intent.putExtra(EXTRA_COUNTER, counter)
        sendBroadcast(intent)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        const val ACTION_COUNTER_UPDATED = "com.example.counter.ACTION_COUNTER_UPDATED"
        const val EXTRA_COUNTER = "com.example.counter.EXTRA_COUNTER"
    }
}
