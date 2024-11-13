import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private lateinit var textViewCounter: TextView
    private lateinit var buttonIncrement: Button
    private lateinit var buttonSort: Button
    private var counter = 0
    private val numbers = (1..10000).shuffled().toIntArray()  // Sample array for sorting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCounter = findViewById(R.id.textViewCounter)
        buttonIncrement = findViewById(R.id.buttonIncrement)
        buttonSort = findViewById(R.id.buttonSort)

        buttonIncrement.setOnClickListener {
            counter++
            textViewCounter.text = "Counter: $counter"
        }

        buttonSort.setOnClickListener {
            lifecycleScope.launch {
                val time = measureTimeMillis {
                    withContext(Dispatchers.Default) {
                        numbers.sort()
                    }
                }
                Toast.makeText(this@MainActivity, "Sorted in $time ms", Toast.LENGTH_SHORT).show()
            }
        }

        fetchAndDisplayPosts()
    }

    private fun fetchAndDisplayPosts() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getPosts()
                if (response.isSuccessful) {
                    val posts = response.body()
                    posts?.let {
                        displayPosts(it)
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Failed to fetch posts", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayPosts(posts: List<Post>) {
        textViewCounter.append("\nPosts:\n")
        posts.forEach { post ->
            textViewCounter.append("${post.title}\n")
        }
    }
}
