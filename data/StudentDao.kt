package com.example.roomproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)

    @Query("""
        SELECT students.id, students.name, students.surname, students.age, groups.name AS groupName 
        FROM students 
        JOIN groups ON students.groupId = groups.id
    """)
    suspend fun getAllStudentsWithGroup(): List<StudentWithGroup>

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteStudentById(id: Int)
}

data class StudentWithGroup(
    val id: Int,
    val name: String,
    val surname: String,
    val age: Int,
    val groupName: String
)
