package com.example.roomproject.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.roomproject.R
import com.example.roomproject.data.AppDatabase
import com.example.roomproject.data.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val editName = findViewById<EditText>(R.id.editStudentName)
        val editSurname = findViewById<EditText>(R.id.editStudentSurname)
        val editAge = findViewById<EditText>(R.id.editStudentAge)
        val spinnerGroup = findViewById<Spinner>(R.id.spinnerGroup)
        val btnSaveStudent = findViewById<Button>(R.id.btnSaveStudent)

        CoroutineScope(Dispatchers.IO).launch {
            val groups = AppDatabase.getDatabase(applicationContext).groupDao().getAllGroups()
            runOnUiThread {
                val adapter = ArrayAdapter(
                    this@AddStudentActivity,
                    android.R.layout.simple_spinner_item,
                    groups.map { it.name }
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerGroup.adapter = adapter
            }
        }

        btnSaveStudent.setOnClickListener {
            val name = editName.text.toString().trim()
            val surname = editSurname.text.toString().trim()
            val age = editAge.text.toString().toIntOrNull()
            val groupName = spinnerGroup.selectedItem?.toString()

            if (name.isNotEmpty() && surname.isNotEmpty() && age != null && groupName != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    val group = AppDatabase.getDatabase(applicationContext).groupDao().getAllGroups()
                        .first { it.name == groupName }
                    val student = Student(name = name, surname = surname, age = age, groupId = group.id)
                    AppDatabase.getDatabase(applicationContext).studentDao().insertStudent(student)
                    runOnUiThread {
                        Toast.makeText(this@AddStudentActivity, "Student added successfully!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
