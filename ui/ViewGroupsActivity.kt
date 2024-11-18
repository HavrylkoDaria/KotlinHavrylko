package com.example.roomproject.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.roomproject.R
import com.example.roomproject.data.AppDatabase
import com.example.roomproject.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewGroupsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_groups)

        val listViewGroups = findViewById<ListView>(R.id.listViewGroups)

        CoroutineScope(Dispatchers.IO).launch {
            val groups = AppDatabase.getDatabase(applicationContext).groupDao().getAllGroups()
            runOnUiThread {
                val adapter = ArrayAdapter(
                    this@ViewGroupsActivity,
                    android.R.layout.simple_list_item_1,
                    groups.map { it.name }
                )
                listViewGroups.adapter = adapter

                listViewGroups.setOnItemClickListener { _, _, position, _ ->
                    val group = groups[position]
                    val intent = Intent(this@ViewGroupsActivity, ViewStudentsActivity::class.java)
                    intent.putExtra(Constants.EXTRA_GROUP_ID, group.id)
                    startActivity(intent)
                }
            }
        }
    }
}
