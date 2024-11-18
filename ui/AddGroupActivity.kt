package com.example.roomproject.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomproject.R
import com.example.roomproject.data.AppDatabase
import com.example.roomproject.data.Group
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_group)

        val editGroupName = findViewById<EditText>(R.id.editGroupName)
        val btnSaveGroup = findViewById<Button>(R.id.btnSaveGroup)

        btnSaveGroup.setOnClickListener {
            val groupName = editGroupName.text.toString().trim()
            if (groupName.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val group = Group(name = groupName)
                    AppDatabase.getDatabase(applicationContext).groupDao().insertGroup(group)
                    runOnUiThread {
                        Toast.makeText(this@AddGroupActivity, "Group added successfully!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Group name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
