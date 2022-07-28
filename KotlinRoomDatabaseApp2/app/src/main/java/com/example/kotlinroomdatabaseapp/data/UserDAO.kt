package com.example.kotlinroomdatabaseapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user:User)

    @Query("SELECT*FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>

    @Query("DELETE FROM USER_TABLE")
    suspend fun deleteAllUser()

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT*FROM  user_table WHERE firstName LIKE :searchQuery OR lastName LIKE:searchQuery")
    fun searchDatabase(searchQuery:String) : kotlinx.coroutines.flow.Flow<List<User>>
}