package com.example.kotlinroomdatabaseapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
    val readAllData:LiveData<List<User>>
    private val userRepository:UserRepository

    init {
        val userDao=UserDatabase.getDatabase(application).userDao()
        userRepository= UserRepository(userDao)
        readAllData=userRepository.readAllData
    }

    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.addUser(user)
        }
    }
    fun updateUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.updateUser(user)
        }
    }
    fun deleteUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.deleteUser(user)
        }
    }
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.deleteAllUser()
        }
    }

    fun searchData(searchQ:String):LiveData<List<User>>{
        return userRepository.searchDatabase(searchQ).asLiveData()
    }

}

