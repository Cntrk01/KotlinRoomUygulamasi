package com.example.kotlinroomdatabaseapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDAO: UserDAO) {
    val readAllData:LiveData<List<User>> = userDAO.readAllData()

    suspend fun addUser(user: User){
        userDAO.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDAO.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDAO.deleteUser(user)
    }
    suspend fun deleteAllUser(){
        userDAO.deleteAllUser()
    }
     fun searchDatabase(searchQuery:String): Flow<List<User>>{
        return userDAO.searchDatabase(searchQuery)
    }

}