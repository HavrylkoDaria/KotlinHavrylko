package com.example.roomproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GroupDao {
    @Insert
    suspend fun insertGroup(group: Group)

    @Query("SELECT * FROM groups")
    suspend fun getAllGroups(): List<Group>
}
