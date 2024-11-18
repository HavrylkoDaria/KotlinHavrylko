package com.example.roomproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.btnAddGroup).setOnClickListener {
            startActivity(Intent(this, AddGroupActivity::class.java))
        }

        findViewById<FloatingActionButton>(R.id.btnAddStudent).setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }

        findViewById<FloatingActionButton>(R.id.btnViewStudents).setOnClickListener {
            startActivity(Intent(this, ViewStudentsActivity::class.java))
        }

        findViewById<FloatingActionButton>(R.id.btnViewGroups).setOnClickListener {
            startActivity(Intent(this, ViewGroupsActivity::class.java))
        }
    }
}
