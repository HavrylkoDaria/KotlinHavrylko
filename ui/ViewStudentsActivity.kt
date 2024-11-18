package com.example.roomproject.ui

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomproject.R
import com.example.roomproject.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewStudentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        val listViewStudents = findViewById<ListView>(R.id.listViewStudents)

        CoroutineScope(Dispatchers.IO).launch {
            val students = AppDatabase.getDatabase(applicationContext).studentDao().getAllStudentsWithGroup()
            runOnUiThread {
                val adapter = ArrayAdapter(
                    this@ViewStudentsActivity,
                    android.R.layout.simple_list_item_1,
                    students.map { "${it.name} ${it.surname}, ${it.age} y.o. (${it.groupName})" }
                )
                listViewStudents.adapter = adapter

                listViewStudents.setOnItemLongClickListener { _, _, position, _ ->
                    val student = students[position]
                    CoroutineScope(Dispatchers.IO).launch {
                        AppDatabase.getDatabase(applicationContext).studentDao().deleteStudentById(student.id)
                        runOnUiThread {
                            Toast.makeText(this@ViewStudentsActivity, "Student deleted!", Toast.LENGTH_SHORT).show()
                            recreate()
                        }
                    }
                    true
                }
            }
        }
    }
}
